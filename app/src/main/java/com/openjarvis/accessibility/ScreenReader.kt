package com.openjarvis.accessibility

import android.content.Context
import android.view.accessibility.AccessibilityNodeInfo
import java.util.ArrayDeque

class ScreenReader private constructor(
    private val serviceProvider: () -> JarvisAccessibilityService?
) {
    constructor(service: JarvisAccessibilityService) : this({ service })
    constructor(@Suppress("UNUSED_PARAMETER") context: Context) : this({ JarvisAccessibilityService.instance })

    fun extractAllText(): String {
        val rootNode = serviceProvider()?.rootInActiveWindow ?: return ""
        val builder = StringBuilder()
        extractTextRecursive(rootNode, builder)
        rootNode.recycle()
        return builder.toString()
    }

    private fun extractTextRecursive(node: AccessibilityNodeInfo, builder: StringBuilder) {
        node.text?.toString()?.takeIf { it.isNotBlank() }?.let { builder.append(it).append(' ') }
        node.contentDescription?.toString()?.takeIf { it.isNotBlank() }?.let { builder.append(it).append(' ') }
        for (i in 0 until node.childCount) {
            node.getChild(i)?.let { child ->
                extractTextRecursive(child, builder)
                child.recycle()
            }
        }
    }

    fun findNodeByText(text: String): AccessibilityNodeInfo? {
        val rootNode = serviceProvider()?.rootInActiveWindow ?: return null
        val result = findNodeRecursive(rootNode, text)
        rootNode.recycle()
        return result
    }

    private fun findNodeRecursive(node: AccessibilityNodeInfo, text: String): AccessibilityNodeInfo? {
        val normalized = text.lowercase()
        val queue = ArrayDeque<AccessibilityNodeInfo>()
        queue.add(node)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            val matches = current.text?.toString()?.lowercase()?.contains(normalized) == true ||
                current.contentDescription?.toString()?.lowercase()?.contains(normalized) == true
            if (matches) return current
            for (i in 0 until current.childCount) current.getChild(i)?.let(queue::add)
            current.recycle()
        }
        return null
    }

    fun findNodeByHint(hint: String): AccessibilityNodeInfo? {
        val rootNode = serviceProvider()?.rootInActiveWindow ?: return null
        val result = findHintRecursive(rootNode, hint)
        rootNode.recycle()
        return result
    }

    private fun findHintRecursive(node: AccessibilityNodeInfo, hint: String): AccessibilityNodeInfo? {
        val normalized = hint.lowercase()
        val queue = ArrayDeque<AccessibilityNodeInfo>()
        queue.add(node)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (current.hintText?.toString()?.lowercase()?.contains(normalized) == true) return current
            for (i in 0 until current.childCount) current.getChild(i)?.let(queue::add)
            current.recycle()
        }
        return null
    }

    fun getFocusedNode(): AccessibilityNodeInfo? {
        val rootNode = serviceProvider()?.rootInActiveWindow ?: return null
        val focused = rootNode.findFocus(AccessibilityNodeInfo.FOCUS_INPUT)
        rootNode.recycle()
        return focused
    }
}

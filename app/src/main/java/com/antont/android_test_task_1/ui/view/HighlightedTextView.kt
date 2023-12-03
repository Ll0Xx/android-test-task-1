package com.antont.android_test_task_1.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.antont.android_test_task_1.R

class HighlightedTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatTextView(context, attrs, defStyle) {

    private var highlightColor: Int
    private var highlightPaddings: Int
    private var highlightPaddingTopExtra: Int
    private var highlightPaddingBottomExtra: Int

    private var paint: Paint = Paint()

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.HighlightedTextView,
            0, 0
        ).apply {
            try {
                highlightColor =
                    getColor(
                        R.styleable.HighlightedTextView_highlightColor,
                        DEFAULT_HIGHLIGHT_COLOR
                    )
                highlightPaddings =
                    getDimensionPixelOffset(
                        R.styleable.HighlightedTextView_highlightPadding,
                        DEFAULT_HIGHLIGHT_PADDING
                    )
                highlightPaddingTopExtra =
                    getDimensionPixelOffset(
                        R.styleable.HighlightedTextView_highlightPaddingTopExtra,
                        DEFAULT_HIGHLIGHT_PADDING_TOP_EXTRA
                    )
                highlightPaddingBottomExtra =
                    getDimensionPixelOffset(
                        R.styleable.HighlightedTextView_highlightPaddingBottomExtra,
                        DEFAULT_HIGHLIGHT_PADDING_BOTTOM_EXTRA
                    )
            } finally {
                recycle()
            }
        }
        paint.color = highlightColor
    }

    override fun draw(canvas: Canvas) {
        val currentLayout = layout
        if (currentLayout == null) {
            super.draw(canvas)
            return
        }
        val lineCount = currentLayout.lineCount
        val rect = Rect()
        for (i in 0 until lineCount) {
            val baseline = getLineBounds(i, rect)
            rect.top = baseline - textSize.toInt() - highlightPaddings - highlightPaddingTopExtra
            rect.left = currentLayout.getLineLeft(i).toInt() - highlightPaddings
            rect.right = currentLayout.getLineRight(i).toInt() + highlightPaddings
            rect.bottom = baseline + highlightPaddings + highlightPaddingBottomExtra
            canvas.drawRect(rect, paint)
        }
        super.draw(canvas)
    }

    companion object {
        private const val DEFAULT_HIGHLIGHT_COLOR: Int = Color.WHITE
        private const val DEFAULT_HIGHLIGHT_PADDING: Int = 0
        private const val DEFAULT_HIGHLIGHT_PADDING_TOP_EXTRA: Int = -17
        private const val DEFAULT_HIGHLIGHT_PADDING_BOTTOM_EXTRA: Int = 0
    }
}
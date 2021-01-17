package com.example.eziketobenna.bakingapp.views

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.example.eziketobenna.bakingapp.views.databinding.SimpleEmptyStateViewLayoutBinding

/**
 * @author MomohGreat on 08/07/19
 */
typealias ActionButtonClickListener = () -> Unit

class SimpleEmptyStateView : LinearLayout {

    private var binding: SimpleEmptyStateViewLayoutBinding

    var buttonClickListener: ActionButtonClickListener? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        binding = SimpleEmptyStateViewLayoutBinding.inflate(inflater, this, true)

        val a = context.obtainStyledAttributes(attrs, R.styleable.SimpleEmptyStateView, 0, 0)

        val emptyStateTitle: String? =
            a.getString(R.styleable.SimpleEmptyStateView_emptyStateTitleText)
        val emptyStateImageSrc: Drawable? =
            a.getDrawable(R.styleable.SimpleEmptyStateView_emptyStateImageSrc)
        val emptyStateCaption: String? =
            a.getString(R.styleable.SimpleEmptyStateView_emptyStateCaptionText)
        val emptyStateButtonText: String? =
            a.getString(R.styleable.SimpleEmptyStateView_emptyStateButtonText)

        val emptyStateButtonVisible: Boolean =
            a.getBoolean(R.styleable.SimpleEmptyStateView_isButtonVisible, false)
        val emptyStateTitleVisible: Boolean =
            a.getBoolean(R.styleable.SimpleEmptyStateView_isTitleVisible, true)

        a.recycle()

        setTitle(emptyStateTitle)

        if (emptyStateTitleVisible) {
            binding.title.visibility = View.VISIBLE
        } else {
            binding.title.visibility = View.INVISIBLE
        }

        setImage(emptyStateImageSrc)

        setCaption(emptyStateCaption)

        if (emptyStateButtonText != null) {
            binding.retryBtn.text = emptyStateButtonText
        }

        if (emptyStateButtonVisible) {
            binding.retryBtn.visibility = View.VISIBLE
        } else {
            binding.retryBtn.visibility = View.GONE
        }

        binding.retryBtn.setOnClickListener {
            buttonClickListener?.invoke()
        }

        val actionBtnBounceAnim =
            ObjectAnimator.ofFloat(binding.image, "translationY", 0f, 25f, 0f)
        actionBtnBounceAnim.interpolator = AccelerateDecelerateInterpolator()
        actionBtnBounceAnim.duration = 3000
        actionBtnBounceAnim.repeatMode = ValueAnimator.RESTART
        actionBtnBounceAnim.repeatCount = ValueAnimator.INFINITE
        actionBtnBounceAnim.start()
    }

    fun setImage(emptyStateImageSrc: Drawable?) {
        if (emptyStateImageSrc != null) {
            binding.image.setImageDrawable(emptyStateImageSrc)
        } else {
            binding.image.visibility = View.INVISIBLE
        }
    }

    var isButtonVisible: Boolean = false
        set(value) {
            field = value
            binding.retryBtn.isVisible = value
        }

    var isTitleVisible: Boolean = false
        set(value) {
            field = value
            binding.title.isVisible = value
        }

    fun setCaption(emptyStateCaption: String?) {
        if (emptyStateCaption != null) {
            binding.caption.text = emptyStateCaption
        }
    }

    fun setTitle(emptyStateTitle: String?) {
        if (emptyStateTitle != null) {
            binding.title.text = emptyStateTitle
        }
    }
}

package com.devmeng.cornershadowlayout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import androidx.constraintlayout.widget.ConstraintLayout
import com.devmeng.skinlib.skin.SkinWidgetSupport
import com.devmeng.skinlib.skin.entity.SkinPair
import com.devmeng.skinlib.skin.utils.SkinResources
import com.devmeng.skinlib.utils.Log

/**
 * Created by Richard -> MHS
 * Date : 2022/6/12  16:54
 * Version : 2
 * Description : 圆角阴影布局
 *
 * ↓ 自定义属性 ↓
 * shadowColor: 阴影颜色
 * shadowRadius: 阴影半径
 * allCornerRadius: 所有圆角半径
 * topLeftRadius: 左上圆角半径
 * topRightRadius: 右上圆角半径
 * bottomLeftRadius: 左下圆角半径
 * bottomRightRadius: 右下圆角半径
 * backRes: 设置背景图片资源 ID（后期更新）
 * backColor: 圆角背景颜色
 * borderColor: 边框颜色
 * borderWidth: 边框宽度
 */
@SuppressLint("ResourceAsColor")
class CornerShadowLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), SkinWidgetSupport {

    override val attrsList: MutableList<String> = mutableListOf(
        "shadowColor",
        "shadowRadius",
        "allCornerRadius",
        "topLeftRadius",
        "topRightRadius",
        "bottomLeftRadius",
        "bottomRightRadius",
        "backRes",
        "backColor",
        "borderColor",
        "borderWidth",
    )

    private var widthMode: Int = 0
    private var heightMode: Int = 0
    private var widthSize: Int = 0
    private var heightSize: Int = 0
    var mWidth: Int = 0
    var mHeight: Int = 0

    //自定义属性
    var borderWidth: Float = 0F
    var shadowRadius: Float = 0F
    var allCornerRadius: Float = 0F
    var topLeftRadius: Float = 0F
    var topRightRadius: Float = 0F
    var bottomLeftRadius: Float = 0F
    var bottomRightRadius: Float = 0F
    var backRes: Int = 0
    var borderColor: Int = R.color.color_black_333
    var backColor: Int = R.color.color_white_FFF
    var shadowColor: Int = R.color.color_black_333

    //画笔
    private val cornerBackPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var borderPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CornerShadowLayout)

        with(typedArray) {

            //背景相关
            backRes =
                getResourceId(R.styleable.CornerShadowLayout_backRes, backRes)
            backColor =
                getColor(
                    R.styleable.CornerShadowLayout_backColor,
                    getColor(backColor)
                )

            //布局边框相关
            borderWidth = getDimension(
                R.styleable.CornerShadowLayout_borderWidth,
                borderWidth
            )
            borderColor = getColor(
                R.styleable.CornerShadowLayout_borderColor,
                getColor(borderColor)
            )

            //布局阴影相关
            shadowRadius = getDimension(
                R.styleable.CornerShadowLayout_shadowRadius,
                0F
            )
            shadowColor =
                getColor(
                    R.styleable.CornerShadowLayout_shadowColor,
                    R.color.color_black_333
                )
            //圆角相关
            allCornerRadius = getDimension(
                R.styleable.CornerShadowLayout_allCornerRadius,
                allCornerRadius
            )
            topLeftRadius = getDimension(
                R.styleable.CornerShadowLayout_topLeftRadius,
                topLeftRadius
            )
            topRightRadius = getDimension(
                R.styleable.CornerShadowLayout_topRightRadius,
                topRightRadius
            )
            bottomLeftRadius = getDimension(
                R.styleable.CornerShadowLayout_bottomLeftRadius,
                bottomLeftRadius
            )
            bottomRightRadius = getDimension(
                R.styleable.CornerShadowLayout_bottomRightRadius,
                bottomRightRadius
            )

            recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        initConfig()
    }

    @SuppressLint("ResourceAsColor")
    fun initConfig() {
        setWillNotDraw(false)
        if (allCornerRadius > 0F) {
            topLeftRadius = allCornerRadius
            topRightRadius = allCornerRadius
            bottomLeftRadius = allCornerRadius
            bottomRightRadius = allCornerRadius
        }
        //背景画笔
        cornerBackPaint.apply {
            color = backColor
            style = Paint.Style.FILL_AND_STROKE
            setShadowLayer(
                shadowRadius,
                0F,
                0F,
                shadowColor
            )
        }

        //边框画笔
        borderPaint.apply {
            color = borderColor
            style = Paint.Style.STROKE
            strokeWidth = borderWidth
        }

        //必须关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null)

    }

    private fun getRadiusArray(): FloatArray {
        return floatArrayOf(
            topLeftRadius,
            topLeftRadius,
            topRightRadius,
            topRightRadius,
            bottomRightRadius,
            bottomRightRadius,
            bottomLeftRadius,
            bottomLeftRadius,
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        widthMode = MeasureSpec.getMode(widthMeasureSpec)
        heightMode = MeasureSpec.getMode(heightMeasureSpec)
        widthSize = MeasureSpec.getSize(widthMeasureSpec)
        heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val widthOffset = widthOffsetConfig(shadowRadius.toInt() * 2)
        val heightOffset = heightOffsetConfig(shadowRadius.toInt() * 2)

        when (widthMode) {
            MeasureSpec.EXACTLY -> {
                mWidth = widthSize + widthOffset
                mHeight = calcHeight() + heightOffset
            }
            MeasureSpec.AT_MOST -> {
                mWidth =
                    (measuredWidth + shadowRadius.toInt() * 2)
                        .coerceAtMost(resources.displayMetrics.widthPixels) + widthOffset
                mHeight = calcHeight() + heightOffset
            }
            else -> {
                mWidth =
                    (measuredWidth + shadowRadius.toInt() * 2)
                        .coerceAtMost(resources.displayMetrics.widthPixels) + widthOffset
                mHeight = calcHeight() + heightOffset
            }
        }
        setMeasuredDimension(mWidth, mHeight)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val offset =
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                shadowRadius,
                resources.displayMetrics
            ).toInt() * 2
        val widthOffset = widthOffsetConfig(offset)

        val heightOffset = heightOffsetConfig(offset)

        if (widthMode == MeasureSpec.AT_MOST) {
            when (heightMode) {
                MeasureSpec.AT_MOST -> {
                    offsetChildOutside(offset, offset, offset, offset)
                }
                MeasureSpec.EXACTLY -> {
                    offsetChildOutside(offset, heightOffset / 2, offset, heightOffset / 2)
                }
                else -> {
                    offsetChildOutside(offset, 0, offset, 0)
                }
            }
        } else if (widthMode == MeasureSpec.EXACTLY) {
            when (heightMode) {
                MeasureSpec.AT_MOST -> {
                    offsetChildOutside(widthOffset / 2, offset, widthOffset / 2, offset)
                }
                MeasureSpec.EXACTLY -> {
                    offsetChildOutside(
                        widthOffset / 2,
                        heightOffset / 2,
                        widthOffset / 2,
                        heightOffset / 2
                    )
                }
                else -> {
                    offsetChildOutside(offset, 0, offset, 0)
                }
            }
        } else {
            if (heightMode == MeasureSpec.AT_MOST) {
                offsetChildOutside(0, offset, 0, offset)
            }
        }

    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        with(canvas!!) {
            val offset = (borderWidth + shadowRadius)
            val bgRectF = RectF(
                offset,
                offset,
                mWidth - offset,
                mHeight - offset
            )
            val bgPath = Path()
            bgPath.addRoundRect(bgRectF, getRadiusArray(), Path.Direction.CW)
            drawPath(bgPath, cornerBackPaint)

            val borderRectF = RectF(
                offset,
                offset,
                mWidth - offset,
                mHeight - offset
            )

            val borderPath = Path()
            borderPath.addRoundRect(borderRectF, getRadiusArray(), Path.Direction.CW)
            drawPath(borderPath, borderPaint)

        }
    }

    private fun widthOffsetConfig(offset: Int): Int {
        val screenWidth = resources.displayMetrics.widthPixels
        if (widthMode == MeasureSpec.EXACTLY) {
            if (widthSize == screenWidth) {
                return 0
            }
        }
        return offset
    }

    private fun heightOffsetConfig(offset: Int): Int {
        val screenHeight = resources.displayMetrics.heightPixels
        if (heightMode == MeasureSpec.EXACTLY) {
            if (heightSize == screenHeight) {
                return 0
            }
        }
        return offset
    }

    private fun offsetChildOutside(left: Int, top: Int, right: Int, bottom: Int) {
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            Log.d("top [${view.top}] left [${view.left}] right [${view.right}] bottom [${view.bottom}]")
            view.layout(
                view.left + left,
                view.top + top,
                view.right + right,
                view.bottom + bottom
            )
        }
    }

    private fun calcHeight(): Int =
        if (heightMode == MeasureSpec.EXACTLY) {
            heightSize
        } else {
            (measuredHeight + shadowRadius.toInt() * 2).coerceAtMost(heightSize)
        }

    private fun getColor(color: Int) = context.getColor(color)

    /**
     * 释放资源
     * 1.取消关闭硬件加速
     */
    fun release() {
        //todo other
        setLayerType(LAYER_TYPE_NONE, null)
    }

    /**
     * 应用自定义 View 的皮肤包
     */
    override fun applySkin(pairList: List<SkinPair>) {
        for ((attrName, resId) in pairList) {
            Log.d("attrName -> [$attrName] resId -> [$resId]")
            when (attrName) {
                "shadeColor" -> {
                    shadowColor = SkinResources.instance.getColor(resId)
                }
                "shadeRadius" -> shadowRadius = SkinResources.instance.getDimension(resId)
                "allCornerRadius" -> {
                    allCornerRadius = SkinResources.instance.getDimension(resId)
                }
                "topLeftRadius" -> {
                    topLeftRadius = SkinResources.instance.getDimension(resId)
                }
                "topRightRadius" -> {
                    topRightRadius = SkinResources.instance.getDimension(resId)
                }
                "bottomLeftRadius" -> {
                    bottomLeftRadius = SkinResources.instance.getDimension(resId)
                }
                "bottomRightRadius" -> {
                    bottomRightRadius = SkinResources.instance.getDimension(resId)
                }
                "backRes" -> {
                    TODO()
                }
                "backColor" -> {
                    backColor = SkinResources.instance.getColor(resId)
                }
                "borderColor" -> {
                    borderColor = SkinResources.instance.getColor(resId)
                }
                "borderWidth" -> {
                    borderWidth = SkinResources.instance.getDimension(resId)
                }
            }
        }
        //重新为画笔的阴影上色及量宽
        update()
    }

    fun update() {
        initConfig()
        postInvalidate()
    }

}
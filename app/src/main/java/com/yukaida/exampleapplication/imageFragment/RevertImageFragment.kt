package com.yukaida.exampleapplication.imageFragment

import android.graphics.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import coil.load
import coil.transform.CircleCropTransformation
import com.yukaida.exampleapplication.R
import com.yukaida.exampleapplication.databinding.FragmentRevertImageBinding

//倒影ImageView
class RevertImageFragment : Fragment() {

    private val vb by lazy {
        FragmentRevertImageBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(vb) {
            //示例图片
            imgPic.load(R.drawable.cat) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }

            revertWithOrigin()
            drawOriginAndRevert()

        }
    }

    // A  在原图下方添加一个imageView 用原图 倒置 并添加遮罩效果
    private fun revertWithOrigin() {
        //获取示例图片的bitmap
        val bitmapOrigin = BitmapFactory.decodeResource(resources, R.drawable.cat)

        //把原图倒过来
        val matrix = Matrix()
        matrix.preScale(1f, -1f)

        //→↓ xy 开始选取的顶点位置, width height 截取的宽高
        val bitmapRevert = Bitmap.createBitmap(
            bitmapOrigin,
            0,
            0,
            bitmapOrigin.width,
            bitmapOrigin.height,
            matrix,
            false
        )

        val canvas = Canvas(bitmapRevert)

        //画出渐变遮罩效果
        val shaderPaint = Paint()
        val linearGradient = LinearGradient(
            0f,
            0f,
            bitmapRevert.width.toFloat(),
            bitmapRevert.height.toFloat(),
            getColor(requireContext(), R.color.white_t40),
            getColor(requireContext(), R.color.transparent),
            Shader.TileMode.MIRROR
        )

        shaderPaint.shader = linearGradient
        shaderPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN);

        canvas.drawRect(
            0f,
            0f,
            bitmapRevert.width.toFloat(),
            bitmapRevert.height.toFloat(),
            shaderPaint
        )

        vb.imgPicRevert.load(bitmapRevert) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }

    //B  新建一个画布 画出原图和倒影
    private fun drawOriginAndRevert() {
        //原图
        val bitmapOrigin = BitmapFactory.decodeResource(resources, R.drawable.cat)

        //
        val matrix = Matrix()
        matrix.preScale(1f, -1f)

        //倒影 取原图下半部分进行倒置
        val bitmapRevert =
            Bitmap.createBitmap(
                bitmapOrigin,
                0,
                bitmapOrigin.height / 2,
                bitmapOrigin.width,
                bitmapOrigin.height / 2, matrix, false
            )

        //原图和倒影之间的空隙
        val spacer = 8

        val bitmapOAndR =
            Bitmap.createBitmap(
                bitmapOrigin.width,
                (bitmapOrigin.height * 1.5).toInt() + spacer,
                Bitmap.Config.ARGB_8888
            )

        val canvas = Canvas(bitmapOAndR)
        val paint = Paint()
        //画出 原图
        canvas.drawBitmap(bitmapOrigin, 0f, 0f, paint)
        //画出 倒影
        canvas.drawBitmap(bitmapRevert, 0f, bitmapOrigin.height.toFloat() + spacer, paint)

        //画出 渐变遮罩效果
        val shaderPaint = Paint()
        //线性渐变 着色器 定义渐变两个顶点的位置
        val linearGradient = LinearGradient(
            0f,
            bitmapOrigin.height.toFloat() + spacer,
            0f,
            bitmapRevert.height.toFloat() + spacer,
            getColor(requireContext(), R.color.white_t40),
            getColor(requireContext(), R.color.transparent),
            Shader.TileMode.MIRROR
        )

        shaderPaint.shader = linearGradient
        shaderPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN);

        //使用渐变着色器 画出渐变区域
        canvas.drawRect(
            0f,
            bitmapOrigin.height.toFloat() + spacer,
            bitmapRevert.width.toFloat(),
            bitmapOAndR.height.toFloat() + spacer,
            shaderPaint
        )

//        val centerX=bitmapOrigin.width.toFloat()/2
//        val centerY=bitmapOrigin.height.toFloat()
//
//        canvas.save()
//        val camera=Camera()
//        camera.save() // 保存 Camera 的状态
//        camera.rotateX(30f) // 旋转 Camera 的三维空间
//        canvas.translate(centerX, centerY) // 旋转之后把投影移动回来
//        camera.applyToCanvas(canvas) // 把旋转投影到 Canvas
//        canvas.translate(-centerX, -centerY) // 旋转之前把绘制内容移动到轴心（原点）
//        camera.restore() // 恢复 Camera 的状态
//
//        canvas.drawBitmap(bitmapOAndR, 0f, bitmapOAndR.height+spacer.toFloat(), paint);
//        canvas.restore()

        vb.imgPicRevertAndOrigin.load(bitmapOAndR)
    }

}
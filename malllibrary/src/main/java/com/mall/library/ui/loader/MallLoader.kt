package com.mall.library.ui.loader

import android.app.AppComponentFactory
import android.content.Context
import android.support.v7.app.AppCompatDialog
import android.view.Gravity
import com.blankj.utilcode.util.ScreenUtils
import com.mall.library.R
import com.wang.avi.AVLoadingIndicatorView
import com.wang.avi.Indicator
import com.wang.avi.indicators.BallClipRotateIndicator

object MallLoader {
    //默认的loader
    private val DEFAULT_LOADER=BallClipRotateIndicator()

    private val LOADER_SIZE_SCALE=8
    private val LOADER_OFFSET_SCALE=10

    private val LOADERS=ArrayList<AppCompatDialog>()

    private fun createDialog(
        context: Context?,
        loadingView:AVLoadingIndicatorView
        ):AppCompatDialog{

        val dialog=AppCompatDialog(context, R.style.dialog)
        //获取屏幕的宽高
        val deviceWidth=ScreenUtils.getScreenWidth()
        val deviceHeight=ScreenUtils.getScreenHeight()

        val dialogWindow=dialog.window

        dialog.setContentView(loadingView)

        if(dialogWindow!=null){
            val lp=dialogWindow.attributes
            lp.width=deviceWidth/ LOADER_SIZE_SCALE

            lp.height=deviceHeight/ LOADER_SIZE_SCALE
            lp.height=lp.height+deviceHeight/ LOADER_OFFSET_SCALE

            lp.gravity=Gravity.CENTER

        }

        LOADERS.add(dialog)

        return dialog
    }

    fun showLoading(context:Context?,type:Enum<Style>)
    {
        showLoading(context,type.name)


    }

    fun showLoading(context:Context?,type:String)
    {
        val avLoadingIndicatorView =AVLoadingIndicatorView(context)
        avLoadingIndicatorView.setIndicator(type)
        createDialog(context,avLoadingIndicatorView).show()
    }

    @JvmOverloads
    fun showLoading(context:Context?,indicator: Indicator= DEFAULT_LOADER)
    {
        val avLoadingIndicatorView =AVLoadingIndicatorView(context)
        avLoadingIndicatorView.setIndicator(indicator)
        createDialog(context,avLoadingIndicatorView).show()

    }

    fun stopLoading(){
        for(dialog in LOADERS){
            if(dialog.isShowing){
                dialog.cancel()
            }
        }
        LOADERS.clear()
    }





}
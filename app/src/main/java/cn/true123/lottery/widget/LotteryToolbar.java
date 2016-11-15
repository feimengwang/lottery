package cn.true123.lottery.widget;

import android.content.Context;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by feimeng0530 on 2016/3/23.
 */
public class LotteryToolbar  extends Toolbar{
    public LotteryToolbar(Context context) {
        super(context);
    }

    public LotteryToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LotteryToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void getPrivateMethod(){
        try {
            Field f = this.getClass().getField("mMenuView");
            f.setAccessible(true);
            ActionMenuView mMenuView = (ActionMenuView) f.get(this);
            // private ActionMenuPresenter mPresenter;
            if(mMenuView!=null){
              Field fp=  mMenuView.getClass().getDeclaredField("mPresenter");
                BaseMenuPresenter mPresenter = (BaseMenuPresenter) fp.get(mMenuView);
                if(mPresenter!=null){
                   // mPresenter
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void inflateMenu(@MenuRes int resId) {
        Menu m = getMenu();
        if(m!=null &&m.getClass()== MenuBuilder.class){
            try {
                Method method = m.getClass().getDeclaredMethod("setOptionalIconsVisible",Boolean.TYPE);
                method.setAccessible(true);
                method.invoke(m,true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        super.inflateMenu(resId);
    }

    @Override
    public boolean showOverflowMenu() {
        return super.showOverflowMenu();
    }
}

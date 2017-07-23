package com.example;

import javax.lang.model.type.TypeMirror;

/**
 * Created by Administrator on 2017/7/22.
 */

public class FindViewBind {
    private String name ; //MainActivity textView

    private TypeMirror typeMirror;// textView Class TextView

    private int resId; //R.id.textView

    public FindViewBind(String name, TypeMirror typeMirror, int resId) {
        this.name = name;
        this.typeMirror = typeMirror;
        this.resId = resId;
    }
}

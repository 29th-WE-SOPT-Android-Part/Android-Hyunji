// Generated by view binder compiler. Do not edit!
package com.example.androidseminar.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.androidseminar.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySignInBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnLogin;

  @NonNull
  public final Button btnRegister;

  @NonNull
  public final EditText homeIdEdit;

  @NonNull
  public final TextView homeIdTv;

  @NonNull
  public final EditText homePwEdit;

  @NonNull
  public final TextView homePwTv;

  @NonNull
  public final TextView homeTitleTv;

  private ActivitySignInBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnLogin,
      @NonNull Button btnRegister, @NonNull EditText homeIdEdit, @NonNull TextView homeIdTv,
      @NonNull EditText homePwEdit, @NonNull TextView homePwTv, @NonNull TextView homeTitleTv) {
    this.rootView = rootView;
    this.btnLogin = btnLogin;
    this.btnRegister = btnRegister;
    this.homeIdEdit = homeIdEdit;
    this.homeIdTv = homeIdTv;
    this.homePwEdit = homePwEdit;
    this.homePwTv = homePwTv;
    this.homeTitleTv = homeTitleTv;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySignInBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySignInBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_sign_in, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySignInBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_login;
      Button btnLogin = rootView.findViewById(id);
      if (btnLogin == null) {
        break missingId;
      }

      id = R.id.btn_register;
      Button btnRegister = rootView.findViewById(id);
      if (btnRegister == null) {
        break missingId;
      }

      id = R.id.home_id_edit;
      EditText homeIdEdit = rootView.findViewById(id);
      if (homeIdEdit == null) {
        break missingId;
      }

      id = R.id.home_id_tv;
      TextView homeIdTv = rootView.findViewById(id);
      if (homeIdTv == null) {
        break missingId;
      }

      id = R.id.home_pw_edit;
      EditText homePwEdit = rootView.findViewById(id);
      if (homePwEdit == null) {
        break missingId;
      }

      id = R.id.home_pw_tv;
      TextView homePwTv = rootView.findViewById(id);
      if (homePwTv == null) {
        break missingId;
      }

      id = R.id.home_title_tv;
      TextView homeTitleTv = rootView.findViewById(id);
      if (homeTitleTv == null) {
        break missingId;
      }

      return new ActivitySignInBinding((ConstraintLayout) rootView, btnLogin, btnRegister,
          homeIdEdit, homeIdTv, homePwEdit, homePwTv, homeTitleTv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

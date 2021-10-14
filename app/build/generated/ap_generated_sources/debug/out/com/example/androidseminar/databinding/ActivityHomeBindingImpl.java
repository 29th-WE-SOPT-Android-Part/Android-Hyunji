package com.example.androidseminar.databinding;
import com.example.androidseminar.R;
import com.example.androidseminar.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityHomeBindingImpl extends ActivityHomeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.home_title_tv, 4);
        sViewsWithIds.put(R.id.home_profile_iv, 5);
        sViewsWithIds.put(R.id.home_nameW_tv, 6);
        sViewsWithIds.put(R.id.home_ageW_tv, 7);
        sViewsWithIds.put(R.id.home_mbtiW_tv, 8);
        sViewsWithIds.put(R.id.home_hello1_tv, 9);
        sViewsWithIds.put(R.id.home_hello2_tv, 10);
        sViewsWithIds.put(R.id.home_hello3_tv, 11);
        sViewsWithIds.put(R.id.home_hello4_tv, 12);
        sViewsWithIds.put(R.id.home_hello5_tv, 13);
        sViewsWithIds.put(R.id.home_githubicon_iv, 14);
    }
    // views
    @NonNull
    private final android.widget.ScrollView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityHomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private ActivityHomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[7]
            , (android.widget.ImageView) bindings[14]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[6]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.TextView) bindings[4]
            );
        this.homeAgeTv.setTag(null);
        this.homeMbtiTv.setTag(null);
        this.homeNameTv.setTag(null);
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.user == variableId) {
            setUser((com.example.androidseminar.User) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setUser(@Nullable com.example.androidseminar.User User) {
        this.mUser = User;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.user);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String userAge = null;
        com.example.androidseminar.User user = mUser;
        java.lang.String userMbti = null;
        java.lang.String userName = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (user != null) {
                    // read user.age
                    userAge = user.getAge();
                    // read user.mbti
                    userMbti = user.getMbti();
                    // read user.name
                    userName = user.getName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.homeAgeTv, userAge);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.homeMbtiTv, userMbti);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.homeNameTv, userName);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): user
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}
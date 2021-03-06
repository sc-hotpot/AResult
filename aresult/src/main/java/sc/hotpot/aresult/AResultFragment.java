package sc.hotpot.aresult;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;


import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public class AResultFragment extends Fragment {
    private PublishSubject<AResultMessage> mSubject;
    private Disposable mDisposable;
    private AResult.Callback mCallback;

    public static AResultFragment newInstance() {
        return new AResultFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public Observable<AResultMessage> startForResult(final Intent intent, final int requestCode) {
        mCallback = null;
        mSubject = PublishSubject.create();
        return mSubject.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                mDisposable = disposable;
                startActivityForResult(intent, requestCode);
            }
        });
    }

    public void startForResult(Intent intent, int requestCode, AResult.Callback callback) {
        mSubject = null;
        mCallback = callback;
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AResultMessage message = new AResultMessage(data, resultCode, requestCode);
        if (mSubject != null) {
            mSubject.onNext(message);
            mSubject.onComplete();
        }
        if (mCallback != null) {
            mCallback.onActivityResult(message);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mDisposable != null && !mDisposable.isDisposed())
            mDisposable.dispose();
    }
}
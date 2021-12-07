
package com.fidelreactlibrary;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.fidelapi.Fidel;
import com.fidelapi.entities.abstraction.OnResultObserver;
import com.fidelreactlibrary.adapters.abstraction.ConstantsProvider;
import com.fidelreactlibrary.adapters.abstraction.DataProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class FidelModule extends ReactContextBaseJavaModule {

  private final DataProcessor<ReadableMap> setupProcessor;
  private final List<ConstantsProvider> constantsProviderList;
  private final OnResultObserver onResultObserver;

  public FidelModule(ReactApplicationContext reactContext,
                     DataProcessor<ReadableMap> setupProcessor,
                     OnResultObserver onResultObserver,
                     List<ConstantsProvider> constantsProviderList) {
    super(reactContext);
    this.setupProcessor = setupProcessor;
    this.constantsProviderList = constantsProviderList;
    this.onResultObserver = onResultObserver;
  }

  @NonNull
  @Override
  public String getName() {
    return "NativeFidelBridge";
  }

  @ReactMethod
  public void addListener(String eventName) {
    Fidel.onResult = onResultObserver;
  }

  @ReactMethod
  public void removeListeners(Integer count) {
    Fidel.onResult = null;
  }

  @ReactMethod
  public void start() {
    final Activity activity = getCurrentActivity();
    if (activity != null) {
        Fidel.start(activity);
    }
  }

  @ReactMethod
  public void setup(ReadableMap map) {
    setupProcessor.process(map);
  }

  @Nullable
  @Override
  public Map<String, Object> getConstants() {
    Map<String, Object> constants = new HashMap<>();
    for (ConstantsProvider provider: constantsProviderList) {
      constants.putAll(provider.getConstants());
    }
    return constants;
  }
}
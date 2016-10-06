package com.example.rafaelalves.mvp_reactiveretrofit.repository.listeners;

public interface BaseListener {

    /**
     * Indicates that request started loading
     */
    void onRequestStart();

    /**
     * Indicates that request finished loading
     */
    void onRequestFinish();

    /**
     * Error was thrown during request
     *
     * @param throwable - Throwable Exception
     */
    void onError(Throwable throwable);
}

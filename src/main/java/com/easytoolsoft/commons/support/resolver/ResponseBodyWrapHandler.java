package com.easytoolsoft.commons.support.resolver;

import com.easytoolsoft.commons.support.model.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author zhiwei.deng
 * @date 2017-04-26
 **/
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {
    private final String basePackage;
    private final HandlerMethodReturnValueHandler delegate;

    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate, String basePackage) {
        this.delegate = delegate;
        this.basePackage = basePackage;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {

        if (this.isWrapReturnValue(returnValue, returnType)) {
            returnValue = new ResponseResult<>(returnValue);
        }
        delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }

    /**
     * 如果returnValue为ResponseResult类型
     * 或 basePackage为空
     * 或 不是调用类的前辍
     * 则返回false(不需要包装@ResponseBody返回值)
     *
     * @param returnValue 返回值
     * @param returnType  返回值类型
     * @return true|false
     */
    private boolean isWrapReturnValue(Object returnValue, MethodParameter returnType) {
        if (returnValue instanceof ResponseResult) {
            return false;
        }
        if (StringUtils.isBlank(this.basePackage)) {
            return false;
        }
        String declaringClazzName = returnType.getMethod().getDeclaringClass().getName();
        return StringUtils.startsWith(declaringClazzName, this.basePackage);
    }
}
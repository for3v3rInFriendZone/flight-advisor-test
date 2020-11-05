package com.flight.advisor.util;

import lombok.experimental.UtilityClass;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.ResourceBundle;

@UtilityClass
public class ResourceBundleResolver {

    private final String BUNDLE_LOCATION = "i18n.errors";

    public String getResourceByKey(String key) {
        final Locale currentLocale = LocaleContextHolder.getLocale();

        return ResourceBundle.getBundle(BUNDLE_LOCATION, currentLocale).getString(key);
    }
}


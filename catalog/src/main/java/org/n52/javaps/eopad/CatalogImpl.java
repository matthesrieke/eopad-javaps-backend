/*
 * Copyright 2019 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.n52.javaps.eopad;

import okhttp3.Credentials;
import okhttp3.HttpUrl;
import org.n52.shetland.ogc.wps.ap.ApplicationPackage;

public class CatalogImpl implements Catalog {
    private static final String SERVICES = "services";
    private final HttpUrl url;

    public CatalogImpl(String url) {
        this.url = HttpUrl.get(url);
    }

    @Override
    public HttpUrl getURL() {
        return url.newBuilder().addPathSegment(SERVICES).build();
    }

    @Override
    public HttpUrl getURL(String id) {
        return url.newBuilder().addPathSegment(SERVICES).addPathSegment(id).build();
    }

    @Override
    public String getURL(ApplicationPackage applicationPackage) {
        return getURL(applicationPackage.getProcessDescription().getProcessDescription().getId().getValue()).toString();
    }
}
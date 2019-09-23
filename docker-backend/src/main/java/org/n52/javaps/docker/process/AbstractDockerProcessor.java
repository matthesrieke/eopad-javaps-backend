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
package org.n52.javaps.docker.process;

import org.n52.javaps.algorithm.ExecutionException;
import org.n52.javaps.docker.Environment;
import org.n52.shetland.ogc.wps.Format;

public abstract class AbstractDockerProcessor<T, V> extends DelegatingDockerJobConfig {

    protected AbstractDockerProcessor(DockerJobConfig config) {
        super(config);
    }

    protected void createFormat(Environment environment, Format format) {
        format.getMimeType().ifPresent(mimeType -> environment.put(Environment.MIME_TYPE, mimeType));
        format.getSchema().ifPresent(schema -> environment.put(Environment.SCHEMA, schema));
        format.getEncoding().ifPresent(encoding -> environment.put(Environment.ENCODING, encoding));
    }

    public abstract V process(T input) throws ExecutionException;
}
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
package org.n52.javaps.docker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class DockerConfigImpl implements DockerConfig {
    private String dataPath = "/data";
    private String inputPath = "/data/inputs";
    private String outputPath = "/data/outputs";
    private String dockerHost;
    private String user;
    private String group;
    private Duration timeout;
    private Environment environment;
    private String javaPsVersion;

    @Override
    public Optional<String> getGroup() {
        return Optional.ofNullable(this.group);
    }

    @Value("${docker.group:}")
    public void setGroup(String group) {
        this.group = group == null || group.isEmpty() ? null : group;
    }

    @Override
    public Optional<String> getUser() {
        return Optional.ofNullable(this.user);
    }

    @Value("${docker.user:}")
    public void setUser(String user) {
        this.user = user == null || user.isEmpty() ? null : user;
    }

    @Override
    public Optional<Duration> getTimeout() {
        return Optional.ofNullable(timeout);
    }

    public void setTimeout(Duration timeout) {
        this.timeout = timeout;
    }

    @Value("${docker.timeout:PT30M}")
    public void setTimeout(String timeout) {
        setTimeout(timeout == null ? null : Duration.parse(timeout));
    }

    @Override
    public String getJavaPsVersion() {
        return javaPsVersion;
    }

    @Value("${javaPS.version:1.4.0}")
    public void setJavaPsVersion(String javaPsVersion) {
        this.javaPsVersion = javaPsVersion;
    }

    @Value("${docker.inputPath:/data/inputs}")
    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    @Override
    public String getInputPath() {
        return inputPath;
    }

    @Override
    public String getInputPath(String file) {
        return combinePath(inputPath, file);
    }

    @Override
    public String getOutputPath() {
        return outputPath;
    }

    @Override
    public String getOutputPath(String file) {
        return combinePath(outputPath, file);
    }

    @Value("${docker.outputPath:/data/outputs}")
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    @Override
    public String getDataPath() {
        return dataPath;
    }

    @Value("${docker.dataPath:/data}")
    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    @Override
    public Environment getGlobalEnvironment() {
        return environment;
    }

    @Override
    public String getDockerHost() {
        return dockerHost;
    }

    @Value("${docker.host:unix:///var/run/docker.sock}")
    public void setDockerHost(String dockerHost) {
        this.dockerHost = dockerHost;
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    private String combinePath(String directory, String file) {
        return String.format("%s/%s", directory, file);
    }

}
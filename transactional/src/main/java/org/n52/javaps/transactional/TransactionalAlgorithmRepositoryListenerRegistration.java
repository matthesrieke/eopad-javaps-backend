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
package org.n52.javaps.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Component
public class TransactionalAlgorithmRepositoryListenerRegistration {
    private Set<TransactionalAlgorithmRepositoryListener> listeners = Collections.emptySet();
    private Set<ListenableTransactionalAlgorithmRepository> repositories = Collections.emptySet();

    @Autowired(required = false)
    public void setListeners(Set<TransactionalAlgorithmRepositoryListener> listeners) {
        this.listeners = Optional.ofNullable(listeners).orElseGet(Collections::emptySet);
    }

    @Autowired(required = false)
    public void setRepositories(Set<ListenableTransactionalAlgorithmRepository> repositories) {
        this.repositories = Optional.ofNullable(repositories).orElseGet(Collections::emptySet);
    }

    @PostConstruct
    public void register() {
        repositories.forEach(repository -> listeners.forEach(repository::addListener));
    }

    @PreDestroy
    public void deregister() {
        repositories.forEach(repository -> listeners.forEach(repository::removeListener));
    }
}
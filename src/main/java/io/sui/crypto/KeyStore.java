/*
 * Copyright 2022-2023 281165273grape@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.sui.crypto;


import java.util.NavigableSet;

/**
 * The interface Key store.
 *
 * @author grapebaba
 * @since 2022.11
 */
public interface KeyStore {

  /**
   * Gets by address.
   *
   * @param address the address
   * @return the by address
   */
  SuiKeyPair<?> getByAddress(String address);

  /**
   * Addresses navigable set.
   *
   * @return the navigable set
   */
  NavigableSet<String> addresses();

  /**
   * Add by address.
   *
   * @param address the address
   * @param keyPair the keyPair
   */
  void addKey(String address, SuiKeyPair<?> keyPair);
}

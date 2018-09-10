/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.example.engine;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.interceptor.CommandInterceptor;

/**
 * @author Daniel Meyer
 *
 */
public class EngineNameCommandInterceptorPlugin extends AbstractProcessEnginePlugin {

  protected EngineNameCommandInterceptor engineNameCommandInterceptor = new EngineNameCommandInterceptor();

  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
	  
	  
    // add command interceptor to configuration
    List<CommandInterceptor> customPreCommandInterceptorsTxRequired = processEngineConfiguration.getCustomPreCommandInterceptorsTxRequired();
    if(customPreCommandInterceptorsTxRequired == null) {
      customPreCommandInterceptorsTxRequired = new ArrayList<CommandInterceptor>();
      processEngineConfiguration.setCustomPreCommandInterceptorsTxRequired(customPreCommandInterceptorsTxRequired);
    }
    customPreCommandInterceptorsTxRequired.add(engineNameCommandInterceptor);

    List<CommandInterceptor> customPreCommandInterceptorsTxRequiresNew = processEngineConfiguration.getCustomPreCommandInterceptorsTxRequiresNew();
    if(customPreCommandInterceptorsTxRequiresNew == null) {
      customPreCommandInterceptorsTxRequiresNew = new ArrayList<CommandInterceptor>();
      processEngineConfiguration.setCustomPreCommandInterceptorsTxRequiresNew(customPreCommandInterceptorsTxRequiresNew);
    }
    customPreCommandInterceptorsTxRequired.add(engineNameCommandInterceptor);
  }

  public void postProcessEngineBuild(ProcessEngine processEngine) {
	  engineNameCommandInterceptor.setEngineName(processEngine.getProcessEngineConfiguration().getProcessEngineName());
    // after the process engine is built, start blocking
  }

  /**
   * @return the blockingCommandInterceptor
   */
  public EngineNameCommandInterceptor getBlockingCommandInterceptor() {
    return engineNameCommandInterceptor;
  }

}

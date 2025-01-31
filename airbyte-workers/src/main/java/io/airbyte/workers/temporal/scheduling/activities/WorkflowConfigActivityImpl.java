/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.workers.temporal.scheduling.activities;

import io.airbyte.commons.temporal.config.WorkerMode;
import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of the {@link WorkflowConfigActivity} that is managed by the application framework
 * and therefore has access to the configuration loaded by the framework.
 */
@Slf4j
@Singleton
@Requires(env = WorkerMode.CONTROL_PLANE)
public class WorkflowConfigActivityImpl implements WorkflowConfigActivity {

  private final Long workflowRestartDelaySeconds;

  public WorkflowConfigActivityImpl(@Property(name = "airbyte.workflow.failure.restart-delay",
                                              defaultValue = "600") final Long workflowRestartDelaySeconds) {
    this.workflowRestartDelaySeconds = workflowRestartDelaySeconds;
  }

  @Override
  public Duration getWorkflowRestartDelaySeconds() {
    return Duration.ofSeconds(workflowRestartDelaySeconds);
  }

}

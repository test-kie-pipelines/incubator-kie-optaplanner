/*
 * Copyright 2011 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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

package org.drools.planner.core.solver;

import org.drools.planner.core.Solver;
import org.drools.planner.core.score.Score;
import org.drools.planner.core.score.director.ScoreDirector;
import org.drools.planner.core.solution.Solution;

/**
 * A ProblemFactChange represents a change in 1 or more problem facts of a {@link Solution}.
 * Problem facts used by a {@link Solver} must not be changed while it is solving,
 * but by scheduling this command to the {@link Solver}, you can change them when the time is right.
 * <p/>
 * Note that the {@link Solver} clones a {@link Solution} at will.
 * So any change must be done on the problem facts and planning entities referenced by the {@link Solution}
 * of the {@link ScoreDirector}. On each change it should also notify the {@link ScoreDirector} accordingly.
 */
public interface ProblemFactChange {

    /**
     * Does the change on the {@link Solution} of the {@link ScoreDirector}
     * and notifies the {@link ScoreDirector} accordingly.
     * Every modification to the {@link Solution}, must be correctly notified to the {@link ScoreDirector},
     * otherwise the {@link Score} calculation will be corrupted.
     * @param scoreDirector never null
     * Contains the working {@link Solution} which contains the planning facts (and planning entities) to change.
     * Also needs to get notified of those changes.
     */
    void doChange(ScoreDirector scoreDirector);

}

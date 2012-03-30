/*
 * Copyright 2012 JBoss Inc
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

package org.drools.planner.core.move.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.drools.planner.core.domain.entity.PlanningEntityDescriptor;
import org.drools.planner.core.domain.solution.SolutionDescriptor;
import org.drools.planner.core.domain.variable.PlanningVariableDescriptor;
import org.drools.planner.core.localsearch.LocalSearchSolverPhaseScope;
import org.drools.planner.core.move.Move;
import org.drools.planner.core.move.factory.AbstractMoveFactory;
import org.drools.planner.core.score.director.ScoreDirector;
import org.drools.planner.core.solution.Solution;

// TODO Unify me into the normal GenericSwapMoveFactory
public class GenericChainedSwapMoveFactory extends AbstractMoveFactory {

    private SolutionDescriptor solutionDescriptor;
    private ScoreDirector scoreDirector;

    @Override
    public void phaseStarted(LocalSearchSolverPhaseScope localSearchSolverPhaseScope) {
        super.phaseStarted(localSearchSolverPhaseScope);
        solutionDescriptor = localSearchSolverPhaseScope.getSolutionDescriptor();
        scoreDirector = localSearchSolverPhaseScope.getScoreDirector();
    }

    public List<Move> createMoveList(Solution solution) {
        List<Move> moveList = new ArrayList<Move>();
        List<Object> entityList = solutionDescriptor.getPlanningEntityList(solution);
        for (ListIterator<Object> leftIt = entityList.listIterator(); leftIt.hasNext();) {
            Object leftEntity = leftIt.next();
            PlanningEntityDescriptor leftEntityDescriptor = solutionDescriptor.getPlanningEntityDescriptor(
                    leftEntity.getClass());
            Collection<PlanningVariableDescriptor> variableDescriptors
                    = leftEntityDescriptor.getPlanningVariableDescriptors();
            for (ListIterator<Object> rightIt = entityList.listIterator(leftIt.nextIndex()); rightIt.hasNext();) {
                Object rightEntity = rightIt.next();
                PlanningEntityDescriptor rightEntityDescriptor = solutionDescriptor.getPlanningEntityDescriptor(
                        leftEntity.getClass());
                if (leftEntityDescriptor.getPlanningEntityClass().equals(
                        rightEntityDescriptor.getPlanningEntityClass())) {
                    throw new UnsupportedOperationException("Not yet implemented");
//                    moveList.add(new GenericChainedSwapMove(variableDescriptors,
//                            leftEntity, rightEntity));
                }
            }
        }
        return moveList;
    }

    @Override
    public void phaseEnded(LocalSearchSolverPhaseScope localSearchSolverPhaseScope) {
        super.phaseEnded(localSearchSolverPhaseScope);
        solutionDescriptor = null;
        scoreDirector = null;
    }

}

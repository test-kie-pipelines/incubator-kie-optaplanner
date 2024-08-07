/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.optaplanner.persistence.jpa.api.score.buildin.hardsoftbigdecimal;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import org.optaplanner.core.api.score.buildin.hardsoftbigdecimal.HardSoftBigDecimalScore;

@Converter
public class HardSoftBigDecimalScoreConverter implements AttributeConverter<HardSoftBigDecimalScore, String> {

    @Override
    public String convertToDatabaseColumn(HardSoftBigDecimalScore score) {
        if (score == null) {
            return null;
        }

        return score.toString();
    }

    @Override
    public HardSoftBigDecimalScore convertToEntityAttribute(String scoreString) {
        if (scoreString == null) {
            return null;
        }

        return HardSoftBigDecimalScore.parseScore(scoreString);
    }
}

/*
 * Copyright (C) 2018 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.sergehelfrich.ersaapi.dataaccess;

import eu.sergehelfrich.ersaapi.entities.Reading;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author helfrich
 */
public interface ReadingRepositoryCustom {

    @Query(value = "SELECT * FROM `reading` INNER JOIN(SELECT origin, MAX(`timestamp`) AS maxtimestamp FROM `reading` GROUP BY `origin`) mts ON reading.origin = mts.origin AND timestamp = maxtimestamp",
            nativeQuery=true)
    public List<Reading> findLatest();
    
    @Query("SELECT r FROM Reading r WHERE origin = :origin AND timestamp BETWEEN :minTime AND :maxTime")
    public List<Reading> findRangeByOrigin(@Param("origin") String origin, @Param("minTime") long minTime, @Param("maxTime") long maxTime);

}

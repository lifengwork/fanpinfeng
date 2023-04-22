/*
 *
 * Copyright 2017-2018 549477611@qq.com(xiaoyu)
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.dromara.raincat.core.service;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * AspectTransactionService.
 *
 * @author xiaoyu
 */
@FunctionalInterface
public interface AspectTransactionService {


    /**
     * Invoke object.
     *
     * @param transactionGroupId the transaction group id
     * @param point              the point
     * @return the object
     * @throws Throwable the throwable
     */
    Object invoke(String transactionGroupId, ProceedingJoinPoint point) throws Throwable;
}

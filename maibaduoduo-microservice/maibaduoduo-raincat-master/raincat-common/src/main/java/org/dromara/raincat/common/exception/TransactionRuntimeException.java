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

package org.dromara.raincat.common.exception;

/**
 * TransactionRuntimeException.
 * @author xiaoyu
 */
public class TransactionRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -1949770547060521702L;

    public TransactionRuntimeException() {
    }

    public TransactionRuntimeException(final String message) {
        super(message);
    }

    public TransactionRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TransactionRuntimeException(final Throwable cause) {
        super(cause);
    }
}

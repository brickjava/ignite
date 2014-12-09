/* @java.file.header */

/*  _________        _____ __________________        _____
 *  __  ____/___________(_)______  /__  ____/______ ____(_)_______
 *  _  / __  __  ___/__  / _  __  / _  / __  _  __ `/__  / __  __ \
 *  / /_/ /  _  /    _  /  / /_/ /  / /_/ /  / /_/ / _  /  _  / / /
 *  \____/   /_/     /_/   \_,__/   \____/   \__,_/  /_/   /_/ /_/
 */

package org.gridgain.grid.kernal.visor.misc;

import org.gridgain.grid.*;
import org.gridgain.grid.kernal.processors.task.*;
import org.gridgain.grid.kernal.visor.*;
import org.gridgain.grid.util.typedef.internal.*;

/**
 * Task for collecting latest version.
 */
@GridInternal
public class VisorLatestVersionTask extends VisorOneNodeTask<Void, String> {
    /** */
    private static final long serialVersionUID = 0L;

    /** {@inheritDoc} */
    @Override protected VisorLatestVersionJob job(Void arg) {
        return new VisorLatestVersionJob(arg, debug);
    }

    /**
     * Job for collecting latest version.
     */
    private static class VisorLatestVersionJob extends VisorJob<Void, String> {
        /** */
        private static final long serialVersionUID = 0L;

        /**
         * @param arg Formal job argument.
         * @param debug Debug flag.
         */
        private VisorLatestVersionJob(Void arg, boolean debug) {
            super(arg, debug);
        }

        /** {@inheritDoc} */
        @Override protected String run(Void arg) throws GridException {
            return g.product().latestVersion();
        }

        /** {@inheritDoc} */
        @Override public String toString() {
            return S.toString(VisorLatestVersionJob.class, this);
        }
    }
}

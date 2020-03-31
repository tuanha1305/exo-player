/*
 * Copyright (C) 2016 The Android Open Source Project
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
package io.github.tuanictu97.exo.dash;

import android.os.SystemClock;
import androidx.annotation.Nullable;
import io.github.tuanictu97.exo.core.Format;
import io.github.tuanictu97.exo.core.source.chunk.ChunkSource;
import io.github.tuanictu97.exo.core.trackselection.TrackSelection;
import io.github.tuanictu97.exo.core.upstream.LoaderErrorThrower;
import io.github.tuanictu97.exo.core.upstream.TransferListener;
import io.github.tuanictu97.exo.dash.manifest.DashManifest;

import java.util.List;

/**
 * An {@link ChunkSource} for DASH streams.
 */
public interface DashChunkSource extends ChunkSource {

  /** Factory for {@link DashChunkSource}s. */
  interface Factory {

    /**
     * @param manifestLoaderErrorThrower Throws errors affecting loading of manifests.
     * @param manifest The initial manifest.
     * @param periodIndex The index of the corresponding period in the manifest.
     * @param adaptationSetIndices The indices of the corresponding adaptation sets in the period.
     * @param trackSelection The track selection.
     * @param elapsedRealtimeOffsetMs If known, an estimate of the instantaneous difference between
     *     server-side unix time and {@link SystemClock#elapsedRealtime()} in milliseconds,
     *     specified as the server's unix time minus the local elapsed time. If unknown, set to 0.
     * @param enableEventMessageTrack Whether to output an event message track.
     * @param closedCaptionFormats The {@link Format Formats} of closed caption tracks to be output.
     * @param transferListener The transfer listener which should be informed of any data transfers.
     *     May be null if no listener is available.
     * @return The created {@link DashChunkSource}.
     */
    DashChunkSource createDashChunkSource(
            LoaderErrorThrower manifestLoaderErrorThrower,
            DashManifest manifest,
            int periodIndex,
            int[] adaptationSetIndices,
            TrackSelection trackSelection,
            int type,
            long elapsedRealtimeOffsetMs,
            boolean enableEventMessageTrack,
            List<Format> closedCaptionFormats,
            @Nullable PlayerEmsgHandler.PlayerTrackEmsgHandler playerEmsgHandler,
            @Nullable TransferListener transferListener);
  }

  /**
   * Updates the manifest.
   *
   * @param newManifest The new manifest.
   */
  void updateManifest(DashManifest newManifest, int periodIndex);

  /**
   * Updates the track selection.
   *
   * @param trackSelection The new track selection instance. Must be equivalent to the previous one.
   */
  void updateTrackSelection(TrackSelection trackSelection);
}

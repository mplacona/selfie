package uk.co.placona.selfie;

/**
 * Copyright (C) 2016 mplacona.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Creates a new instance of the Selfie module
 */
class SelfieInstance {
    private final String format;
    private final String path;
    private final int quality;

    String getFormat() {
        return format;
    }

    String getPath() {
        return path;
    }

    int getQuality() {
        return quality;
    }

    private SelfieInstance(SelfieBuilder builder) {
        this.format = builder.format;
        this.path = builder.path;
        this.quality = builder.quality;
    }

    static class SelfieBuilder {
        private String format;
        private String path;
        private int quality;

        SelfieBuilder format(String format) {
            this.format = format;
            return this;
        }

        SelfieBuilder path(String path) {
            this.path = path;
            return this;
        }

        SelfieBuilder quality(int quality) {
            this.quality = quality;
            return this;
        }

        SelfieInstance build() {
            return new SelfieInstance(this);
        }
    }
}

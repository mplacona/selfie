/*
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

package uk.co.placona.selfie;

import android.app.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
public class SelfieTest {
    @Test(expected = IllegalStateException.class)
    public void testSelfieNotInitializedThrows() {
        Activity mockActivity = mock(Activity.class);

        Selfie.snap(mockActivity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFormatThrows() {
        String format = null;
        new Selfie.Builder().format(format);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullPathThrows() {
        File path = null;
        new Selfie.Builder().path(path);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidQualityThrows() {
        int quality = -1;
        new Selfie.Builder().quality(quality);
    }

    @Test
    public void testBuilderCreatesCorrectInstance() {
        String format = "format";
        File path = new File("path");
        int quality = 50;

        Selfie selfie = new Selfie.Builder()
                .format(format)
                .path(path)
                .quality(quality)
                .build();

        assertEquals(format, selfie.format);
        assertEquals(path, selfie.path);
        assertEquals(quality, selfie.quality);
    }
}

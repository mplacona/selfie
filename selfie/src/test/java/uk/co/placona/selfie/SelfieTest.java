package uk.co.placona.selfie;
import android.os.Environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

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

@RunWith(PowerMockRunner.class)
@PrepareForTest({Environment.class, File.class})
public class SelfieTest {

    @Mock
    private File mDirectory;

    @Test
    public void instanceOfSelfieIsCorrect(){
        Selfie selfie = new Selfie();
        assertThat(selfie, instanceOf(Selfie.class));
    }

    private void mockEnvironment(){
        mockStatic(Environment.class, File.class);

        when(Environment.getExternalStorageDirectory())
                .thenReturn(mDirectory);
    }

    @Test
    public void instanceOfSelfieBuilderIsCorect(){
        mockEnvironment();

        Selfie.Builder selfieBuilder = new Selfie.Builder();
        assertThat(selfieBuilder, instanceOf(Selfie.Builder.class));
    }

}

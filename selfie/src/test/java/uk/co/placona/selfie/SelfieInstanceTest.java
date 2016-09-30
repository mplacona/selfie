package uk.co.placona.selfie;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

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

public class SelfieInstanceTest {
    @Test
    public void instanceOfSelfieBuilderisCorrect(){
        SelfieInstance.SelfieBuilder instance = new SelfieInstance.SelfieBuilder();
        assertThat(instance, instanceOf(SelfieInstance.SelfieBuilder.class));
    }

    @Test
    public void instanceOfSelfieReturnsCorrectFormat(){
        SelfieInstance.SelfieBuilder instance = new SelfieInstance.SelfieBuilder();
        String mask = "someFormat";
        SelfieInstance selfieInstance = instance.format(mask).build();

        assertThat(selfieInstance.getFormat(), is(mask));

    }

    @Test
    public void instanceOfSelfieReturnsCorrectPath(){
        SelfieInstance.SelfieBuilder instance = new SelfieInstance.SelfieBuilder();
        String path = "somePath";
        SelfieInstance selfieInstance = instance.path(path).build();

        assertThat(selfieInstance.getPath(), is(path));

    }

    @Test
    public void instanceOfSelfieReturnsCorrectQuality(){
        SelfieInstance.SelfieBuilder instance = new SelfieInstance.SelfieBuilder();
        int quality = 100;
        SelfieInstance selfieInstance = instance.quality(quality).build();

        assertEquals(selfieInstance.getQuality(), quality);

    }

    @Test
    public void instanceOfSelfieReturnsCorrectFormatPathAndQuality(){
        SelfieInstance.SelfieBuilder instance = new SelfieInstance.SelfieBuilder();
        String mask = "someFormat";
        String path = "somePath";
        int quality = 100;
        SelfieInstance selfieInstance = instance
                .format(mask)
                .path(path)
                .quality(quality)
                .build();

        assertThat(selfieInstance.getFormat(), is(mask));
        assertThat(selfieInstance.getPath(), is(path));
        assertEquals(selfieInstance.getQuality(), quality);

    }
}

/*
 * Copyright 2014 - 2019 Blazebit.
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

package test.sample;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.view.EntityViewSetting;
import java.util.List;
import test.model.Cat;
import test.model.Person;
import test.view.CatSimpleView;
import test.view.CatWithOwnerView;
import test.view.PersonSimpleView;
import org.junit.Assert;
import org.junit.Test;
import test.view.PersonView;

public class SampleTest extends AbstractSampleTest {
    
    @Test
    public void sampleTest() {
        transactional(em -> {
            CriteriaBuilder<Person> catCriteriaBuilder = cbf.create(em, Person.class);
            catCriteriaBuilder.from(Person.class, "p").where("name").eq("P2");

            EntityViewSetting<PersonView, CriteriaBuilder<PersonView>> setting = EntityViewSetting.create(PersonView.class);
            CriteriaBuilder<PersonView> cb = evm.applySetting(setting, catCriteriaBuilder);
            List<PersonView> list = cb.getResultList();
            
            System.out.println(list);
            Assert.assertEquals(1, list.size());
        });
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniajc.services.test;

import java.util.Comparator;
import org.junit.internal.runners.InitializationError;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Sorter;

/**
 *
 * @author luis.leon
 */

public class DescriptionSorterRunner extends JUnit4ClassRunner{
    
    
        private static final Comparator<Description> comparator = new Comparator<Description>() {

            public int compare(Description o1, Description o2) {
                return o1.getDisplayName().compareTo(o2.getDisplayName());
            }
        };

        public DescriptionSorterRunner(Class<?> klass) throws InitializationError {
            super(klass);
            sort(new Sorter(comparator));
    }
    
}

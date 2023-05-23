package com.zz.edrt.dataextract.feature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class DataMain {

    static Logger logger = LoggerFactory.getLogger(ClassMetricsHandler.class);

    public static final String CLASS = "class";

    public static final String EVOLUTION = "evolution";

    public static final String LOC = "loc";
    public static final String USAGE = "usage";
    public static final String FEATURES = "features";

    public boolean genFeature(String pro){

        try{
            ClassMetricsHandler classMetricsHandler = new ClassMetricsHandler(CLASS);
            classMetricsHandler.readAndHandleData(pro);

            EvolutionMetricsHandler evolutionMetricsHandler = new EvolutionMetricsHandler(EVOLUTION);
            evolutionMetricsHandler.readAndHandleData(pro);

            LocMetricsHandler locMetricsHandler = new LocMetricsHandler(LOC);
            locMetricsHandler.readAndHandleData(pro);

            UsageMetricsHandler usageMetricsHandler = new UsageMetricsHandler(USAGE);
            usageMetricsHandler.readAndHandleData(pro);

            FeatureCombiner featureCombiner = new FeatureCombiner(FEATURES);
            featureCombiner.readAndHandleData(pro);

            new MergeAllProFeatures().merge(pro);
            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }
}

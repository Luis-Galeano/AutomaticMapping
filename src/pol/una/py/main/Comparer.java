package pol.una.py.main;

import semilar.data.Sentence;
import semilar.sentencemetrics.BLEUComparer;
import semilar.sentencemetrics.CorleyMihalceaComparer;
import semilar.sentencemetrics.DependencyComparer;
import semilar.sentencemetrics.GreedyComparer;
import semilar.sentencemetrics.LSAComparer;
import semilar.sentencemetrics.LexicalOverlapComparer;
import semilar.sentencemetrics.MeteorComparer;
import semilar.sentencemetrics.OptimumComparer;
import semilar.sentencemetrics.PairwiseComparer;
import semilar.tools.semantic.WordNetSimilarity;
import semilar.wordmetrics.LDAWordMetric;
import semilar.wordmetrics.LSAWordMetric;
import semilar.wordmetrics.WNWordMetric;

/**
 *
 * @author kpdevila
 */
public class Comparer {
    GreedyComparer greedyComparerWNLin; 
    GreedyComparer greedyComparerWNLeskTanim;
    GreedyComparer greedyComparerLSATasa; 
    GreedyComparer greedyComparerLDATasa; 
    OptimumComparer optimumComparerWNLin;
    OptimumComparer optimumComparerWNLeskTanim;
    OptimumComparer optimumComparerLSATasa;
    OptimumComparer optimumComparerLDATasa;
    DependencyComparer dependencyComparerWnLeskTanim;
    CorleyMihalceaComparer cmComparer;
    MeteorComparer meteorComparer;
    BLEUComparer bleuComparer;
    LSAComparer lsaComparer;
    LexicalOverlapComparer lexicalOverlapComparer;

    public Comparer() {
        
        boolean wnFirstSenseOnly = true;
       //WNWordMetric wnMetricLin = new WNWordMetric(WordNetSimilarity.WNSimMeasure.LESK_TANIM, wnFirstSenseOnly);
        WNWordMetric wnMetricLeskTanim = new WNWordMetric(WordNetSimilarity.WNSimMeasure.LCH, wnFirstSenseOnly);
        //LSAWordMetric lsaMetricTasa = new LSAWordMetric("LSA-MODEL-TASA-LEMMATIZED-DIM300");
        //LDAWordMetric ldaMetricTasa = new LDAWordMetric("LDA-MODEL-TASA-LEMMATIZED-TOPIC300");

        //greedyComparerWNLin = new GreedyComparer(wnMetricLin, 0.2f, false);
        //greedyComparerWNLeskTanim = new GreedyComparer(wnMetricLeskTanim, 0.3f, false);
        //greedyComparerLSATasa = new GreedyComparer(lsaMetricTasa, 0.6f, false);
        //greedyComparerLDATasa = new GreedyComparer(ldaMetricTasa, 0.3f, false);

        //optimumComparerWNLin = new OptimumComparer(wnMetricLin, 0.0f, true, PairwiseComparer.WordWeightType.IDF, PairwiseComparer.NormalizeType.MIN);
        optimumComparerWNLeskTanim = new OptimumComparer(wnMetricLeskTanim, 0.0f, true, PairwiseComparer.WordWeightType.IDF, PairwiseComparer.NormalizeType.MIN);
        //optimumComparerLSATasa = new OptimumComparer(lsaMetricTasa, 0.6f, false, PairwiseComparer.WordWeightType.NONE, PairwiseComparer.NormalizeType.AVERAGE);
        //optimumComparerLDATasa = new OptimumComparer(ldaMetricTasa, 0.3f, false, PairwiseComparer.WordWeightType.NONE, PairwiseComparer.NormalizeType.AVERAGE);

        //dependencyComparerWnLeskTanim = new DependencyComparer(wnMetricLeskTanim, 0.3f, true, "NONE", "AVERAGE");

        //cmComparer = new CorleyMihalceaComparer(0.3f, false, "NONE", "par");
        //meteorComparer = new MeteorComparer("/opt/AutomaticEvaluation/");
        //bleuComparer = new BLEUComparer();
        //lexicalOverlapComparer = new LexicalOverlapComparer(true);
       //// lsaComparer = new LSAComparer("LSA-MODEL-TASA-LEMMATIZED-DIM300");
        //saComparer.setUseContentWordsOnly(false);
    }
    
     public float getSimilarities(Sentence sentenceA, Sentence sentenceB) {
        
        //return greedyComparerWNLin.computeSimilarity(sentenceA, sentenceB);
        //return greedyComparerWNLeskTanim.computeSimilarity(sentenceA, sentenceB);
        //return greedyComparerLSATasa.computeSimilarity(sentenceA, sentenceB);
        //return greedyComparerLDATasa.computeSimilarity(sentenceA, sentenceB);
        
        //return optimumComparerWNLin.computeSimilarity(sentenceA, sentenceB);
        return optimumComparerWNLeskTanim.computeSimilarity(sentenceA, sentenceB);
        //return optimumComparerLSATasa.computeSimilarity(sentenceA, sentenceB);
        //return optimumComparerLDATasa.computeSimilarity(sentenceA, sentenceB);
        
        //return dependencyComparerWnLeskTanim.computeSimilarity(sentenceA, sentenceB);
        //return cmComparer.computeSimilarity(sentenceA, sentenceB);
        //return meteorComparer.computeSimilarity(sentenceA, sentenceB);
        //return bleuComparer.computeSimilarity(sentenceA, sentenceB);
        //return lexicalOverlapComparer.computeSimilarity(sentenceA, sentenceB);
        
       // return lsaComparer.computeSimilarity(sentenceA, sentenceB);
    }

    public GreedyComparer getGreedyComparerWNLin() {
        return greedyComparerWNLin;
    }

    public void setGreedyComparerWNLin(GreedyComparer greedyComparerWNLin) {
        this.greedyComparerWNLin = greedyComparerWNLin;
    }

    public GreedyComparer getGreedyComparerWNLeskTanim() {
        return greedyComparerWNLeskTanim;
    }

    public void setGreedyComparerWNLeskTanim(GreedyComparer greedyComparerWNLeskTanim) {
        this.greedyComparerWNLeskTanim = greedyComparerWNLeskTanim;
    }

    public GreedyComparer getGreedyComparerLSATasa() {
        return greedyComparerLSATasa;
    }

    public void setGreedyComparerLSATasa(GreedyComparer greedyComparerLSATasa) {
        this.greedyComparerLSATasa = greedyComparerLSATasa;
    }

    public GreedyComparer getGreedyComparerLDATasa() {
        return greedyComparerLDATasa;
    }

    public void setGreedyComparerLDATasa(GreedyComparer greedyComparerLDATasa) {
        this.greedyComparerLDATasa = greedyComparerLDATasa;
    }

    public OptimumComparer getOptimumComparerWNLin() {
        return optimumComparerWNLin;
    }

    public void setOptimumComparerWNLin(OptimumComparer optimumComparerWNLin) {
        this.optimumComparerWNLin = optimumComparerWNLin;
    }

    public OptimumComparer getOptimumComparerWNLeskTanim() {
        return optimumComparerWNLeskTanim;
    }

    public void setOptimumComparerWNLeskTanim(OptimumComparer optimumComparerWNLeskTanim) {
        this.optimumComparerWNLeskTanim = optimumComparerWNLeskTanim;
    }

    public OptimumComparer getOptimumComparerLSATasa() {
        return optimumComparerLSATasa;
    }

    public void setOptimumComparerLSATasa(OptimumComparer optimumComparerLSATasa) {
        this.optimumComparerLSATasa = optimumComparerLSATasa;
    }

    public OptimumComparer getOptimumComparerLDATasa() {
        return optimumComparerLDATasa;
    }

    public void setOptimumComparerLDATasa(OptimumComparer optimumComparerLDATasa) {
        this.optimumComparerLDATasa = optimumComparerLDATasa;
    }

    public DependencyComparer getDependencyComparerWnLeskTanim() {
        return dependencyComparerWnLeskTanim;
    }

    public void setDependencyComparerWnLeskTanim(DependencyComparer dependencyComparerWnLeskTanim) {
        this.dependencyComparerWnLeskTanim = dependencyComparerWnLeskTanim;
    }

    public CorleyMihalceaComparer getCmComparer() {
        return cmComparer;
    }

    public void setCmComparer(CorleyMihalceaComparer cmComparer) {
        this.cmComparer = cmComparer;
    }

    public MeteorComparer getMeteorComparer() {
        return meteorComparer;
    }

    public void setMeteorComparer(MeteorComparer meteorComparer) {
        this.meteorComparer = meteorComparer;
    }

    public BLEUComparer getBleuComparer() {
        return bleuComparer;
    }

    public void setBleuComparer(BLEUComparer bleuComparer) {
        this.bleuComparer = bleuComparer;
    }

    public LSAComparer getLsaComparer() {
        return lsaComparer;
    }

    public void setLsaComparer(LSAComparer lsaComparer) {
        this.lsaComparer = lsaComparer;
    }

    public LexicalOverlapComparer getLexicalOverlapComparer() {
        return lexicalOverlapComparer;
    }

    public void setLexicalOverlapComparer(LexicalOverlapComparer lexicalOverlapComparer) {
        this.lexicalOverlapComparer = lexicalOverlapComparer;
    }
    
}

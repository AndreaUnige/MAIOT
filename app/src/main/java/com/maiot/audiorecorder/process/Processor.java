package com.maiot.audiorecorder.process;

import org.jtransforms.fft.DoubleFFT_1D;

// https://github.com/wendykierp/JTransforms
// https://engineering.icalculator.com/discrete-fourier-transform-calculator.html
// https://www.szynalski.com/tone-generator/
// https://www.youtube.com/watch?v=ksw2dD-lW6M&ab_channel=MusicinSpace
public class Processor {

    short[] audioData;
    double[] fullFft_doubleSize;
    double[] fftAbs;

    double[] spectrum;
    double[] frequencies;

    int dftLength;
    int spectrumLength;

    int fs;

    DoubleFFT_1D doubleFFT1D;


    public Processor(int sampleFrequencyInHz) {
        this.fs = sampleFrequencyInHz;
    }


    public void run(short[] audioData) {
        this.audioData = audioData;

        removeAverage();
        doFFT();

        computeSpectrum();
        computeFrequenciesAxis();
    }

    public double[] getSpectrum() {
        return spectrum;
    }

    public double[] getFrequencies() {
        return frequencies;
    }

    private void doFFT() {
        prepareData();
        doubleFFT1D.realForwardFull(fullFft_doubleSize);
    }

    private void computeSpectrum() {
        computefftAbs();
        takeFirstFftHalf();
        AbsSquared();
    }

    private void computefftAbs() {
        dftLength = fullFft_doubleSize.length / 2;
        fftAbs = new double[dftLength];

        for (int i = 0; i < dftLength; i++)
            fftAbs[i] = abs(fullFft_doubleSize[2*i], fullFft_doubleSize[2*i+1]);
    }

    private void takeFirstFftHalf() {
        spectrumLength = (int) Math.floor((float) dftLength /2) + 1; //floor[N/2] + 1
        spectrum = new double[spectrumLength];

        spectrum[0] = fftAbs[0];
        spectrum[spectrumLength-1] = fftAbs[spectrumLength-1];

        int lastIndexToDouble = (int)Math.floor((float) (dftLength-1) / 2); //floor[(N-1)/2]
        for (int i = 1; i < lastIndexToDouble; i++)
            spectrum[i] = fftAbs[i] * 2;
    }

    private void AbsSquared() {
        for (int i = 0; i < spectrumLength-1; i++)
            spectrum[i] = 1/(double)spectrumLength * Math.pow(spectrum[i], 2);
    }



    private void computeFrequenciesAxis() {
        frequencies = new double[spectrumLength];

        for (int i = 0; i < spectrumLength; i++)
            frequencies[i] = i * ((double)fs/2) / spectrumLength;
    }



    private void prepareData() {
        fullFft_doubleSize = new double[audioData.length * 2];
        doubleFFT1D = new DoubleFFT_1D(audioData.length);

        for (int i = 0; i < audioData.length; i++)
            fullFft_doubleSize[i] = (float) audioData[i];
    }



    private void removeAverage() {
        double avg = meanValue();
        for (int i = 0; i < audioData.length; i++)
            audioData[i] -= avg;

    }

    private double meanValue() {
        double avg = 0.0d;
        for (int i = 0; i < audioData.length; i++)
            avg += audioData[i];

        return avg / audioData.length;
    }


    private double abs(double re, double im) {
        return Math.sqrt(re*re + im*im);
    }
}

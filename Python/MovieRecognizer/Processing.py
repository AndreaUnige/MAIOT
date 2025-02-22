import pandas as pd
import numpy as np

# https://davidsbatista.net/blog/2017/04/01/document_classification/
class Processing:

    df_movies = None
    titles = None
    np_movies = None

    numMovies = 0

    def __init__(self, df_movies):
        self.df_movies = df_movies
        self.numMovies = self.df_movies.shape[0]
        self.__prepare()

    def get_K_RecommendedMovies(self, toBeRecognize_AsNumPyArray, k):
        distances = self.__computeDistances(toBeRecognize_AsNumPyArray)
        bestIndexes = self.__getBestIndexes(distances, k)

        suggestedTitles = self.__getBestTitles(bestIndexes)
        correspondantNormalizedScores = self.__getNormalizedScores(distances, bestIndexes)

        return suggestedTitles, correspondantNormalizedScores


    def __prepare(self):
        self.titles = list(self.df_movies["title"])
        movies_noTitle_noPlot = self.df_movies.drop(["title", "plot"], axis="columns")
        self.np_movies = movies_noTitle_noPlot.to_numpy()

    def __computeDistances(self, toBeRecognize_AsNumPyArray):
        toBeRecognizeTiled = np.tile(toBeRecognize_AsNumPyArray, (self.numMovies, 1))
        matrixDifferenceSquared = (self.np_movies - toBeRecognizeTiled)**2
        return matrixDifferenceSquared.sum(axis=1)**0.5

    def __getBestIndexes(self, distances, k):
        return np.argsort(distances)[:k]

    def __getBestTitles(self, bestIndexes):
        suggestedTitles = []
        for idx in bestIndexes:
            suggestedTitles.append(self.titles[idx])
        return suggestedTitles

    def __getNormalizedScores(self, distances, bestIndexes):
        scores = []
        for idx in bestIndexes:
            singleScore = distances[idx]
            scores.append(self.__normalizeScore(singleScore))
        return scores

    def __normalizeScore(self, score):
        # return score
        MAX_DISTANCE_VALUE = self.numMovies ** 0.5
        return 100 - (score / MAX_DISTANCE_VALUE) * 100

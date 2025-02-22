from ReadDataset import readData
from Processing import Processing
from Constants import getFullPath, DATA_FOLDER, MOVIES_FILE, FAKE_TEST

import numpy as np
from flask import Flask, request
import json

app = Flask(__name__)

pathToMoviesCsvFile = getFullPath([DATA_FOLDER, MOVIES_FILE])
df_movies = readData(pathToMoviesDataset=pathToMoviesCsvFile)
processor = Processing(df_movies=df_movies)

@app.route("/recognize", methods=["POST"])
def find_k_similar_titles():
    dataAsJson = getDataAsJson()
    return processData(dataAsJson)



def getDataAsJson():
    rawData = request.get_data().decode()
    return json.loads(rawData)


def processData(dataAsJson):
    k = getK(dataAsJson)

    movieToRecognize = getMovieToRecognize(dataAsJson)
    np_movieToRecognize = toNumPy(movieToRecognize)

    suggestedTitles, normalizedScores = processor.get_K_RecommendedMovies(np_movieToRecognize, k=k)

    return toJson(suggestedTitles, normalizedScores)


def getK(dataAsJson):
    return dataAsJson["k"]

def getMovieToRecognize(dataAsJson):
    return dataAsJson["movieToRecognize"]


def toNumPy(movieToRecognize):
    movieFromWhomRetrieveSuggested = np.array([])
    for key, value in movieToRecognize.items():
        movieFromWhomRetrieveSuggested = np.append(movieFromWhomRetrieveSuggested, np.array([value]))
    return movieFromWhomRetrieveSuggested


def toJson(suggestedTitles, normalizedScores):
    titlesScoresAsDictArray = tilesAndScoresAsDictArray(suggestedTitles, normalizedScores)
    return json.dumps({"data": titlesScoresAsDictArray})


def tilesAndScoresAsDictArray(suggestedTitles, normalizedScores):
    titles_scores_asDictArray = []

    for i in range(0, len(suggestedTitles)):
        title = removeSpecialChars(suggestedTitles[i])
        normalizedScore = normalizedScores[i]

        titles_scores_asDictArray.append(singleMovieAsDict(title, normalizedScore))
    return titles_scores_asDictArray


def removeSpecialChars(title):
    title = title.replace('"', "")
    title = title.replace("'", "")
    title = title.replace("\n", "")
    return title


def singleMovieAsDict(title, normalizedScore):
    return {"title": title, "score": normalizedScore}



if __name__ == '__main__':
    # app.run(debug=True)
    app.run(host="0.0.0.0", debug=True)
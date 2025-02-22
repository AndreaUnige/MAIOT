DATA_FOLDER = "DATA"
MOVIES_FILE = "movies_genres_en.csv"


FAKE_TEST = {
    "k": 2,
    "movieToRecognize": {
        "Action": 1,
        "Adult": 0,
        "Adventure": 1,
        "Animation": 0,
        "Biography": 1,
        "Comedy": 1,
        "Crime": 1,
        "Documentary": 0,
        "Drama": 0,
        "Family": 1,
        "Fantasy": 1,
        "Game-Show": 1,
        "History": 1,
        "Horror": 1,
        "Music": 0,
        "Musical": 0,
        "Mystery": 1,
        "News": 0,
        "Reality-TV": 1,
        "Romance": 1,
        "Sci-Fi": 0,
        "Short": 0,
        "Sport": 0,
        "Talk-Show": 0,
        "Thriller": 0,
        "War": 0,
        "Western": 0
    }
}

def getFullPath(pathLevelsAsAList):
    fullPath = ""
    for singleLevel in pathLevelsAsAList:
        fullPath += singleLevel + "/"

    return fullPath[:-1]

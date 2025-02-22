import pandas as pd

def readData(pathToMoviesDataset):
    df_movies = pd.read_csv(pathToMoviesDataset, delimiter='\t')
    # Remove the last column (language)
    df_movies = df_movies.drop("plot_lang", axis="columns")
    return df_movies


# geo-quiz
GeoQuiz Android App (chapter by chapter with challenges and some improvements) developed on the Big Nerd Ranch Android book (2nd ed.)

Challenge details:  
To obtain the point 2 of the challenge please repleace the 'mCheatedQuestions' boolean array with the 'mIsCheater' boolean variable.
From the moment you store if the user is a cheater for every answer it's useless to keep also the 'mIsCheater' variable, because you can get is value from 'mCheatedQuestions[mCurrentIndex]'.

USE c_cs108_chishuen;

DROP TABLE IF EXISTS quiz_summary;

CREATE TABLE quiz_summary (
	quizID CHAR(32),
	quizName CHAR(32),
	creator CHAR(32),
	quizDescription BLOB,
	category CHAR(32),
	tag CHAR(255),
	practiceMode BOOLEAN,
	isRandomOrder BOOLEAN,
	isSinglePage BOOLEAN,
	isCorrection BOOLEAN,
	createTime TIMESTAMP,
	questionType BLOB,
	questionID BLOB,
	numTaken INT,
	numRating INT,
	avgRating FLOAT,
	totalScore INT,
	avgScore FLOAT,
	PRIMARY KEY(quizID)
	UNIQUE(quizName)
);

DROP TABLE IF EXISTS quiz_rating;

CREATE TABLE quiz_rating (
	quizID CHAR(32),
	username CHAR(32),
	rating INT,
	review BLOB,
	PRIMARY KEY(quizID, username)
);

DROP TABLE IF EXISTS quiz_history;

CREATE TABLE quiz_history (
	quizID CHAR(32),
	username CHAR(32),
	submitTime TIMESTAMP,
	timeUsed BIGINT,
	score INT,
	PRIMARY KEY(quizID, username, submitTime)
);

DROP TABLE IF EXISTS question_response;
CREATE TABLE question_response(
	questionID CHAR(32),
	quizID CHAR(32),
	timeLimit INT,
	question BLOB,
	answer BLOB,
	score INT,
	PRIMARY KEY(questionID)
);

DROP TABLE IF EXISTS fill_in_blank;
CREATE TABLE fill_in_blank(
	questionID CHAR(32),
	quizID CHAR(32),
	timeLimit INT,
	question BLOB,
	answer BLOB,
	score INT,
	PRIMARY KEY(questionID)
);

DROP TABLE IF EXISTS multiple_choice;
CREATE TABLE multiple_choice(
	questionID CHAR(32),
	quizID CHAR(32),
	timeLimit INT,
	question BLOB,
	answer BLOB,
	score INT,
	choices BLOB,
	PRIMARY KEY(questionID)
);

DROP TABLE IF EXISTS picture_response;
CREATE TABLE picture_response(
	questionID CHAR(32),
	quizID CHAR(32),
	timeLimit INT,
	question BLOB,
	answer BLOB,
	score INT,
	imageURL BLOB,
	PRIMARY KEY(questionID)
);

DROP TABLE IF EXISTS multiple_answer;
CREATE TABLE multiple_answer(
	questionID CHAR(32),
	quizID CHAR(32),
	timeLimit INT,
	question BLOB,
	answer BLOB,
	score INT,
	numOfAns INT,
	isOrder BOOLEAN,
	PRIMARY KEY(questionID)
);

DROP TABLE IF EXISTS multi_choice_multi_answer;
CREATE TABLE multi_choice_multi_answer(
	questionID CHAR(32),
	quizID CHAR(32),
	timeLimit INT,
	question BLOB,
	answer BLOB,
	score INT,
	choices BLOB,
	PRIMARY KEY(questionID)
);

DROP TABLE IF EXISTS matching;
CREATE TABLE matching(
	questionID CHAR(32),
	quizID CHAR(32),
	timeLimit INT,
	question BLOB,
	answer BLOB,
	score INT,
	numOfOptions INT,
	choices BLOB,
	PRIMARY KEY(questionID)
);

DROP TABLE IF EXISTS message_inbox;
CREATE TABLE message_inbox(
	fromuser BLOB,
	touser BLOB,
	title BLOB, 
	content BLOB,
	read_ornot BOOLEAN,
	sent_time TIMESTAMP,
	emailID BLOB
);

DROP TABLE IF EXISTS message_sentbox;
CREATE TABLE message_sentbox(
	fromuser BLOB,
	touser BLOB,
	title BLOB, 
	content BLOB,
	read_ornot BOOLEAN,
	sent_time TIMESTAMP,
	emailID BLOB
);

DROP TABLE IF EXISTS challenge;
CREATE TABLE challenge(
	fromuser BLOB,
	requested_user BLOB,
	quizID BLOB
);

DROP TABLE IF EXISTS friend_list;
CREATE TABLE friend_list(
	User BLOB,
	friendList BLOB,
	number int
);

DROP TABLE IF EXISTS friend_request;
CREATE TABLE friend_request(
	fromuser BLOB,
	touser BLOB
);

/*below is an example created for simple test*/

INSERT INTO quiz_summary VALUES("Q1","Test Quiz", "shanshuo", "This is a quiz that used for testing",
	"Science", "#Test#Science#Interesting#", true, true, false, true, "2015-03-02 11:28:11",
	"QR,FIB,MC,PR,MA,MCMA,MATCH","QR1,FIB1,MC1,PR1,MA1,MCMA1,MATCH1",8,4,3.75,35,25
);
INSERT INTO question_response VALUES("QR1", "Q1", 0, "What is the lightest metal?", "lithium", 5);
INSERT INTO question_response VALUES("QR2", "", 0, "This is used for generate ID", "", 0);
INSERT INTO fill_in_blank VALUES("FIB1", "Q1", 0, "In December 1903, the Royal Swedish Academy of Sciences awarded Pierre Curie, |, and Henri Becquerel the Nobel Prize in Physics.", "Marie Curie", 5);
INSERT INTO fill_in_blank VALUES("FIB2", "", 0, "This is used for generate ID", "", 0);
INSERT INTO multiple_choice VALUES("MC1", "Q1", 0, "Which gas makes up most of the Earth’s atmosphere?", "Nitrogen", 5, "Oxygen|Nitrogen|Hydrogen|Water");
INSERT INTO multiple_choice VALUES("MC2", "", 0, "This is used for generate ID", "", 0, "");
INSERT INTO picture_response VALUES("PR1", "Q1", 0, "Who is the guy shown in the picture", "Albert Einstein|Einstein", 5, "http://scienceworld.wolfram.com/physics/images/main-physics.gif");
INSERT INTO picture_response VALUES("PR2", "", 0, "This is used for generate ID", "", 0, "");
INSERT INTO multiple_answer VALUES("MA1", "Q1", 0, "Please List three most used type of fossil fuels.", "Coal|Petroleum|Natural Gas", 5, 3, false);
INSERT INTO multiple_answer VALUES("MA2", "", 0, "This is used for generate ID", "", 0, 0, false);
INSERT INTO multi_choice_multi_answer VALUES("MCMA1", "Q1", 0, "Which two of the following chemicals elements are metal?", "Mercury|Copper", 5, "Carbon|Mercury|Copper|Hydrogen");
INSERT INTO multi_choice_multi_answer VALUES("MCMA2", "", 0, "This is used for generate ID", "", 0, "");
INSERT INTO matching VALUES("MATCH1", "Q1", 0, "Please match the numbers with proper description.", "71|115", 5, 2,"Average human lifespan|number of known chemical elments");
INSERT INTO matching VALUES("MATCH2", "", 0, "This is used for generate ID", "", 0, 0, "");

INSERT INTO quiz_history VALUES("Q1", "shanshuo", "2015-03-02 11:30:11", 300000, 30);
INSERT INTO quiz_history VALUES("Q1", "lingzhi", "2015-03-02 11:31:11", 3000000, 35);
INSERT INTO quiz_history VALUES("Q1", "lingzhi", "2015-03-02 11:32:11", 30000, 20);
INSERT INTO quiz_history VALUES("Q1", "shanshuo", "2015-03-02 11:33:11", 200000, 0);
INSERT INTO quiz_history VALUES("Q1", "someone", "2015-03-02 11:34:11", 400000, 25);
INSERT INTO quiz_history VALUES("Q1", "patrick", "2015-03-02 11:35:11", 500000, 30);
INSERT INTO quiz_history VALUES("Q1", "whoishe", "2015-03-02 11:36:11", 300000, 30);
INSERT INTO quiz_history VALUES("Q1", "someone", "2015-03-02 11:37:11", 300000, 30);

INSERT INTO quiz_rating VALUES("Q1", "shanshuo", 5, "Interesting!");
INSERT INTO quiz_rating VALUES("Q1", "lingzhi", 4, "Interesting!HAHA");
INSERT INTO quiz_rating VALUES("Q1", "patrick", 3, "Interesting!NO!!");
INSERT INTO quiz_rating VALUES("Q1", "someone", 3, "Interesting!BORING!");



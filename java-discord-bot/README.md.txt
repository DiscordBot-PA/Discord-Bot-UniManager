# Discord-Bot-PA
This project provides a comprehensive system for managing university timetable, preferences, grading, courses, quizzes, questions and more. The main functionality of the system is described below.

### Prerequisites

Ensure you have the following installed on your local development machine:

- Java SDK 19
- Maven 3.8.1 (or later)
- PostGreSQL 15 (or later)

### Installation

1. Clone the repository to your local machine
2. Navigate to the project directory
3. Build the project using Maven
4. Run the project

## Table of Contents
- Grading System
- Preference System
- Timetable Management
- Quiz System
- RSS Feed
- Timetable Questions

### Commands

#### Grading system

- /addGrade : This functionality allows the addition of grades to a student's record.

  - Restrictions:
      - Only a professor can add grades.
      - A valid registration number of the student must be provided.
      - The grade value should be between 1 and 10 inclusive.
      - The courses that can be graded are Programming Advanced (PA) and Introduction to Programming (IP).

- /showGrade : This function allows you to view the grades of a student.
  - Validation:
      - A valid registration number of the student must be provided.


#### AddGrade:
        - The AddGrade class implements the BotCommand interface and represents a command to add a grade for a student.
        - It takes two parameters in its constructor: gradeRepository and studentRepository, which are responsible for handling the storage and retrieval of grade and student data.
        - The execute method is called when the command is executed.
        - Inside the execute method, it checks if the user executing the command has the role "profesor". If not, it replies with an error message.
        - If the user has the role "profesor", it then checks if the required options (registrationnumber, value, and subject) are provided.
        - If all the options are provided, it extracts the values from the options and performs some validations.
        - If the validations pass, it retrieves the student from the student repository based on the registration number.
        - If the student exists, it creates a new grade object with the provided values and the student, and saves it to the grade repository.
        - Finally, it replies with a success message or an error message if any validation fails.

#### ShowGrades:
        - The ShowGrades class also implements the BotCommand interface and represents a command to show the grades of a student.
        - It takes a studentRepository parameter in its constructor.
        - The execute method is called when the command is executed.
        - Inside the execute method, it checks if the required option (registrationnumber) is provided.
        - If the option is provided, it retrieves the student from the student repository based on the registration number.
        - If the student exists, it retrieves the grades for the student from the grade repository.
        - It then processes the grades and creates a message containing the subject titles and corresponding values for each subject.
        - Finally, it replies with the grades message or an error message if the student or grades are not found.

#### Preference System

- /addPreference : This functionality allows setting preferences for class schedules.
  - Validations:
      - The day of the week should be either Monday or Tuesday.
      - The class hour should be either 8, 10, or 12.
      - The course should be either Programming Advanced (PA) or Introduction to Programming (IP).
      - The group should be either B4, A5, or Course.

- /showPreference : This function allows you to view the set preferences.

#### Timetable Management
- /generateTimetable : This function generates a timetable based on the set preferences.
#### Algorithm
- The algorithm starts by checking the admin privileges of the user. If the user is an admin, the algorithm proceeds to generate the timetable, otherwise it sends a permission error message.

The timetable generation algorithm works as follows:

1. Initialization: It first initializes an array of all possible time slots (allPossibleSlots) based on specified days and hours, along with empty schedules (schedule) and unavailable preferences (unavailablePref). It also sets up the data structures for professors, subjects, and groups.

2. Preference Scheduling: Next, the algorithm iterates over the preferences set in the timetable. If a preferred slot is available, it's added to the schedule and removed from the possible slots. Unavailable preferences are added to unavailablePref.

3. Unavailable Preference Scheduling: The algorithm then iterates over unfulfilled preferences (unavailablePref). For each preference, it tries to find an available slot in the allPossibleSlots array that has no professor assigned yet. It assigns the professor, subject, and group from the preference to the slot and adds it to the schedule.

4. Filling Remaining Slots: After scheduling all preferences, the algorithm fills the remaining available slots. For each slot, it searches for a professor who hasn't already given a course on that day and isn't already scheduled for that slot. If found, it assigns the professor and corresponding subject to the slot. Then, it finds a group that hasn't been assigned to this professor yet, and assigns it to the slot. The slot is then added to the schedule.

    #### Helper Functions:
      - isTimeSlotAvailable: This function checks if a specific time slot is still free. It goes through the list of all possible time slots and checks if the desired one is there.

      - removeTimeSlot: This function is used when a time slot gets scheduled. It takes the time slot out of the list of available ones. After this, nobody else can schedule a class at this time slot.

      - generateAllPossibleSlots: This function is used at the beginning to create the list of all possible time slots. It creates a time slot for each hour of each day when classes can take place.

      - isCourseGivenToday: This function checks if a certain professor has already been scheduled to give a course on a specific day. It is used to prevent a professor from teaching a course more than once a day.

      - isTimeSlotFree: This function checks if a certain professor is free at a specific time. It looks at the schedule and if the professor doesn't have a class at that time, the function says the time slot is free.


- /showTimetable : This function allows you to view the generated timetable.
    - Validation
        - If the timetable has not been generated yet, a warning message will be shown.

#### Quiz System
- /startQuiz : This function allows you to start a quiz.
    - Validation:
      - The course should be either Programming Advanced (PA) or Introduction to Programming (IP).

- /submitQuiz : This function allows you to submit a completed quiz.
    - Validation:
      - The course should be either Programming Advanced (PA) or Introduction to Programming (IP).

The StartQuiz and SubmitQuiz commands are part of a quiz feature in a chatbot or application.

The StartQuiz command is responsible for initiating a quiz. It takes a list of questions as input and filters them based on the subject specified by the user. It then sends the filtered questions as a response to the user.

The SubmitQuiz command is used to submit the answers to the quiz questions. It takes the subject and the user's answers as input. It checks the user's answers against the correct answers for the specific subject and generates a result with a score. It then sends the result to the user.

Both commands are related to the Question class and the QuestionManager class. The Question class represents a single question in the quiz, including the subject, the question itself, multiple choices, and the correct answer. The QuestionManager class is responsible for managing the collection of questions and provides methods to retrieve the questions.

In the CommandManager class, instances of StartQuiz and SubmitQuiz are created and added to the list of available commands. This allows the chatbot or application to handle interactions related to starting and submitting quizzes.

#### RSS Feed

##### The system provides multiple RSS feeds which can be accessed with the !rss command followed by the name of the feed.
      1. !dev
      2. !java
      3. !gaming
      4. !js
      5. !net
      6. !python
      7. !react
      8. !spring

#### Timetable Queries

- /asktimetable function allows you to ask various questions related to the timetable. It accepts up to 5 optional parameters to answer the following questions:
  - When does group X have course Y? (Provide: group + subject)
  - What is the timetable of course X? (Provide: subject)
  - What is the timetable of group X? (Provide: group)
  - What do I have on day X at hour Y? (Provide: day + hour)
  - What classes does professor X have on day Y? (Provide: teacher + day)


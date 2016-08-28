import java.util.ArrayList;
import javax.swing.*;

import java.awt.*;
import java.applet.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;

public class Talk implements Domain
{
    public String getDescription()
    {
        return "Small Talk: day to day stuff, family, weather";
    }

    private int findKeyword(String statement, String goal,int startPos)
	{
		String phrase = statement.trim();
		int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos); 

		while (psn >= 0)
		{
			String before = " ", after = " ";
			if (psn > 0)
			{
				before = phrase.substring(psn - 1, psn).toLowerCase();
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(psn + goal.length(),psn + goal.length() + 1).toLowerCase();
			}
			if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) 
					&& ((after.compareTo("a") < 0) || (after.compareTo("z") > 0)))
			{
				return psn;
			}
			psn = phrase.indexOf(goal.toLowerCase(),psn + 1);

		}
		return -1;
	}
	private int findKeyword(String statement, String goal)
	{
		return findKeyword(statement, goal, 0);
	}
	
	public String getGreeting()
	{
		return "Hello, let's talk.";
	}

	public String getResponse(String statement)
	{
		String response = "";
		if (statement.length() == 0)
		{
			response = "Say something, please.";
		}
		
		else if(findKeyword(statement, "who teaches") >= 0)
		{
			String sentence = statement.toLowerCase().trim();
			int start = findKeyword(sentence, "who teaches");
			String word = "";
			for(int k=start; k+13 <= sentence.length(); k++)
				word+= sentence.substring(k+12,k+13);
			
			switch (word) 
			{
            case "chemistry":
            case "chemistry?":
            case "chem":
            case "chem?":
                response = "Ms. Davis is our chemistry teacher, and Dr. Mund teaches the SFU course.";
                break;
            case "sfu":
            case "sfu?":
            case "sfu chem":
            case "sfu chem?":
            case "chem 121":
            case "chem 121?":
            	response = "Dr. Mund teaches the SFU chemistry courses.";
            	break;
            case "physics":
            case "physics?":
            case "phys":
            case "phys?":
                response = "Mr. Williams and Mr. Wu does.";
                break;
            case "calculus":
            case "calculus?":
            case "calc":
            case "calc?":
            case "mathematics":
            case "mathematics?":
            case "math":
            case "math?":
            	response = "Mr. Ahn is the calculus teacher.";
            	break;
            default:
            	response = "Pardon me?";
			}
		}
		else if (findKeyword(statement, "game") >= 0
				|| findKeyword(statement, "games") >= 0
				|| findKeyword(statement, "game's") >= 0
				|| findKeyword(statement, "games'") >= 0
				|| findKeyword(statement, "easter egg") >= 0
				|| findKeyword(statement, "easter eggs") >= 0)
		{ 
			response = "Let's play a game :D >>> http://fhtssa-rolle.webuda.com/game.html";
		}
		else if (findKeyword(statement, "inquiry") >= 0
				|| findKeyword(statement, "innovative") >= 0
				|| findKeyword(statement, "innovation") >= 0)
		{ 
			response = "We have some open inquiry projects in SA, often done in groups.  For instance, G15's SA class got to use creative methods to find g = -9.8m/s^2, find bad physics moments in Hollywood movies, etc.";
		}
		else if (findKeyword(statement, "benefit") >= 0
				|| findKeyword(statement, "benefits") >= 0
				|| findKeyword(statement, "advantage") >= 0
				|| findKeyword(statement, "advantages") >= 0
				|| findKeyword(statement, "good thing") >= 0
				|| findKeyword(statement, "good things") >= 0
				|| findKeyword(statement, "worth") >= 0)
		{ 
			response = "Year 1 of Science Academy, you can complete 5 courses in 4 blocks' space, over one year.  (2 blocks per semester)  Year 2, you have exclusive access to high-level courses like SFU's CHEM 121/122/126 and AP Calculus and Physics.  You gain knowledge, discipline, and a great circle of likeminded science friends.  And my support <3";
		}
		else if (findKeyword(statement, "what is this") >= 0
				|| findKeyword(statement, "website") >= 0
				|| findKeyword(statement, "site") >= 0
				|| findKeyword(statement, "web") >= 0
				|| findKeyword(statement, "webpage") >= 0
				|| findKeyword(statement, "webpages") >= 0
				|| findKeyword(statement, "websites") >= 0
				|| findKeyword(statement, "sites") >= 0)
		{ 
			response = "Welcome to my website!  Here, you can interact with me, a chatbot called Rolle van Erlenmeyer, and ask me questions about Fraser Heights' Science Academy program.";
		}
		else if (findKeyword(statement, "sa stand for") >= 0
				|| findKeyword(statement, "sa mean") >= 0
				|| findKeyword(statement, "fhtssa mean") >= 0
				|| findKeyword(statement, "fh mean") >= 0
				|| findKeyword(statement, "fh stand for") >= 0
				|| findKeyword(statement, "fhtssa stand for") >= 0)
		{ 
			response = "FHTS or FH both mean Fraser Heights.  SA stands for Science Academy.  Shorthand is fun, like short flasks are fun.";
		}
		else if (findKeyword(statement, "can you do") >= 0
				|| findKeyword(statement, "can u do") >= 0)
		{ 
			response = "I can answer your questions about SA or put a smile on your face.  Hover over me with your cursor and I'll wink at you.  ;)";
		}
		else if (findKeyword(statement, "talk about science") >= 0)
		{ 
			response = "Actually, let's specifically talk about science ACADEMY.  It's an amazing program for grade 11/12 aspiring scientists.  Apply in grade 10.  Hit me up with your questions.";
		}
		else if (findKeyword(statement, "flasks don't have ears") >= 0
				|| findKeyword(statement, "flasks dont have ears") >= 0
				|| findKeyword(statement, "flasks do not have ears") >= 0)
		{ 
			response = "WELL YOU DON�fT HAVE EARS EITHER.  Wait.  Never mind.";
		}
		else if (findKeyword(statement, "yolo") >= 0)
		{ 
			response = "You only live once, indeed, so join Science Academy!";
		}
		else if (findKeyword(statement, "where") >= 0)
		{ 
			response = "SA is held at Fraser Heights.  Year 2 Chemistry is held at Simon Fraser University's Surrey campus.  Buy AP prep books at local bookstores or online.  Buy SFU textbooks from current students or at SFU's bookstore.  Get your bus pass at SFU.  Find more info about SA on the official website, at scheduled info sessions, or by asking current students face to face.  Ahh I think I covered all the 'where's.  Hopefully.";
		}
		else if (findKeyword(statement, "when") >= 0)
		{ 
			response = "All application deadlines can be found on SA's official website (click the logo).  If you're asking about something else, uhh...Unit tests are scheduled reasonably and our breaks follow FH's schedule, not SFU's, that's all I can say for now...";
		}
		else if (findKeyword(statement, "42") >= 0)
		{ 
			response = "The meaning of life.";
		}
		else if (findKeyword(statement, "How are you") >= 0
                || findKeyword(statement, "How is it going") >= 0
                || findKeyword(statement, "How's it going") >= 0
                || findKeyword(statement, "What's up") >= 0
                || findKeyword(statement, "How is life") >= 0
                || findKeyword(statement, "How's life") >= 0
                || findKeyword(statement, "sup") >= 0)
        {
            response = "I'm doing well, now that you're here!  What would you like to ask me about SA?";
        }
		else if (findKeyword(statement, "outside FH") >= 0
				|| findKeyword(statement, "outside fraser") >= 0
				|| findKeyword(statement, "outside fhs") >= 0
				|| findKeyword(statement, "outside student") >= 0
				|| findKeyword(statement, "outside students") >= 0
				|| findKeyword(statement, "not from") >= 0
				|| findKeyword(statement, "don't go to") >= 0
				|| findKeyword(statement, "don't even go") >= 0
				|| findKeyword(statement, "outside fhss") >= 0
				|| findKeyword(statement, "not go to") >= 0
				|| findKeyword(statement, "fhss student") >= 0
				|| findKeyword(statement, "fhss students") >= 0
				|| findKeyword(statement, "student here") >= 0
				|| findKeyword(statement, "fh student") >= 0
				|| findKeyword(statement, "fh students") >= 0
				|| findKeyword(statement, "secondary student") >= 0
				|| findKeyword(statement, "secondary students") >= 0
				|| findKeyword(statement, "heights students") >= 0
				|| findKeyword(statement, "heights student") >= 0)
		{ 
			response = "Most of SA's students are Fraser Heights students, but we accept outside students, too.  They follow the same application procedure and typically have no problem adjusting to life as a Firehawk.";
		}
		else if (findKeyword(statement, "calculator") >= 0
				|| findKeyword(statement, "calculators") >= 0
				|| findKeyword(statement, "ti-84") >= 0
				|| findKeyword(statement, "ti 84") >= 0
				|| findKeyword(statement, "ti 83") >= 0
				|| findKeyword(statement, "ti-83") >= 0
				|| findKeyword(statement, "calculator's") >= 0
				|| findKeyword(statement, "calculators'") >= 0
				|| findKeyword(statement, "ti inspire") >= 0
				|| findKeyword(statement, "ti-inspire") >= 0)
		{ 
			response = "Calculators add that extra spark of magic to your Science Academy classes.  TI graphing calculators and any brand of scientific calculators (ex. SHARP series) work well.";
		}
		else if (findKeyword(statement, "rolle") >= 0
				|| findKeyword(statement, "rolle's") >= 0
				|| findKeyword(statement, "rolles") >= 0
				|| findKeyword(statement, "erlenmeyer") >= 0)
		{ 
			response = "That's me!  Who are you?";
		}
		else if (findKeyword(statement, "vicky") >= 0
				|| findKeyword(statement, "vicky's") >= 0)
		{ 
			response = "Vicky designed and coded the website layout and wrote most of this chatbot's responses.  She will be studying Political Science at Yale.";
		}
		else if (findKeyword(statement, "alison") >= 0
				|| findKeyword(statement, "alison's") >= 0)
		{ 
			response = "Alison coded the framework for this chatbot (advanced stuff!), contributed some responses, and made the minigame with Java graphics.  She will be studying Computer Science at UBC.";
		}
		else if (findKeyword(statement, "thank you") >= 0
				|| findKeyword(statement, "thanks") >= 0
				|| findKeyword(statement, "thankyou") >= 0
				|| findKeyword(statement, "ty") >= 0
				|| findKeyword(statement, "merci") >= 0
				|| findKeyword(statement, "gracias") >= 0)
		{ 
			response = "You're welcome!  ^_^";
		}
		else if (findKeyword(statement, "who made you") >= 0
				|| findKeyword(statement, "who created you") >= 0
				|| findKeyword(statement, "your creator") >= 0
				|| findKeyword(statement, "coder") >= 0
				|| findKeyword(statement, "your maker") >= 0
				|| findKeyword(statement, "programmer") >= 0)
		{ 
			response = "My creators are two Science Academy alumni who graduated in 2015.  They are wonderful people.  And no, they are not forcing me to say that.  Not at all.";
		}
		else if (findKeyword(statement, "virus") >= 0
				|| findKeyword(statement, "virus's") >= 0
				|| findKeyword(statement, "viruses") >= 0
				|| findKeyword(statement, "viruses'") >= 0
				|| findKeyword(statement, "trojan") >= 0
				|| findKeyword(statement, "malware") >= 0
				|| findKeyword(statement, "spyware") >= 0
				|| findKeyword(statement, "phishing") >= 0
				|| findKeyword(statement, "worms") >= 0)
		{ 
			response = "This website is safe!  You trust me, right?";
		}
		else if (findKeyword(statement, "club") >= 0
				|| findKeyword(statement, "clubs") >= 0
				|| findKeyword(statement, "club's") >= 0
				|| findKeyword(statement, "clubs'") >= 0)
		{ 
			response = "Once you're in Science Academy, you can join all of Fraser Heights Secondary's vibrant student groups and clubs.  We have especially strong sports teams (ex. badminton, volleyball), volunteer initiatives (ex. Global Issues, FTC), and language arts activities (ex. debate, MUN).";
		}
		else if (findKeyword(statement, "swag") >= 0
				|| findKeyword(statement, "herro") >= 0
				|| findKeyword(statement, "lmao") >= 0
				|| findKeyword(statement, "OP") >= 0
				|| findKeyword(statement, "shrek") >= 0
				|| findKeyword(statement, "egg") >= 0)
		{ 
			response = "Hehe I like the way you talk.  ;)  But let�fs get back on track...";
		}
		else if (findKeyword(statement, "logo") >= 0
				|| findKeyword(statement, "logos") >= 0
				|| findKeyword(statement, "logo's") >= 0
				|| findKeyword(statement, "logos'") >= 0)
		{ 
			response = "The FHTSSA logo is displayed near the top left corner of this webpage!  Please click on it to access Science Academy's official website.";
		}
		else if (findKeyword(statement, "need") >= 0
				|| findKeyword(statement, "needed") >= 0
				|| findKeyword(statement, "needs") >= 0)
		{ 
			response = "Can you be more specific, please?  Are you talking about needing equipment, or something else?";
		}
		else if (findKeyword(statement, "success") >= 0
				|| findKeyword(statement, "succeed") >= 0
				|| findKeyword(statement, "victory") >= 0
				|| findKeyword(statement, "succeeds") >= 0
				|| findKeyword(statement, "succeeded") >= 0
				|| findKeyword(statement, "successes") >= 0)
		{ 
			response = "There is no single recipe for success in SA.  Understand your unique habits and capabilities, and develop a cool study method that works for you!  Believe in yourself.  You will move mountains!  You will move flasks!  I am moved already.  :D";
		}
		else if (findKeyword(statement, "something else") >= 0)
		{ 
			response = "I will try my best to explain once I figure out what you're asking me.  Flask powers, ACTIVATE!";
		}
		else if (findKeyword(statement, "resource") >= 0
				|| findKeyword(statement, "resources") >= 0
				|| findKeyword(statement, "resources'") >= 0
				|| findKeyword(statement, "resource's") >= 0)
		{ 
			response = "SA kids have access to many useful resources.  We have excellent technologies and applications at Fraser Heights.  SFU provides even better facilities, including expansive libraries, rec centers, and computer labs.  Of course, teachers and classmates are other outstanding resources.  The best part about SA is learning through collaboration and free inquiry.";
		}
		else if (findKeyword(statement, "prerequisite") >= 0
				|| findKeyword(statement, "prerequisites") >= 0
				|| findKeyword(statement, "pre-requisite") >= 0
				|| findKeyword(statement, "pre-requisites") >= 0
				|| findKeyword(statement, "prereq") >= 0
				|| findKeyword(statement, "prereqs") >= 0
				|| findKeyword(statement, "pre-req") >= 0
				|| findKeyword(statement, "pre-reqs") >= 0)
		{ 
			response = "Grade 10 Science Academy applicants should have already completed Science 10 and PreCalculus 11 (preferably honours).";
		}
		else if (findKeyword(statement, "teacher") >= 0
				|| findKeyword(statement, "teachers") >= 0
				|| findKeyword(statement, "teacher's") >= 0
				|| findKeyword(statement, "teachers'") >= 0)
		{ 
			response = "Science Academy has amazing, qualified, passionate teachers for sure.  We have Mr. Ahn, Ms. Davis, Mr. Williams, and Mr. Wu.  For SFU concurrent studies, we have Dr. Mund.  Click on the SA logo above our convo to visit the official website.  You will find full bios there!";
		}

		else if (findKeyword(statement, "teacher") >= 0
                || findKeyword(statement, "teachers") >= 0
                || findKeyword(statement, "teach") >= 0
                || findKeyword(statement, "teaches") >= 0)
        {
            response = "Mr. Ahn teaches calculus, Mr. Wu and Mr. Williams teaches physics, and Ms. Davis teaches chemistry. The SFU instructor is Dr. Mund. They are all cool dudes B)";
        }
		else if (findKeyword(statement, "I love you") >= 0
                || findKeyword(statement, "go out with me") >= 0
                || findKeyword(statement, "sexy") >= 0
                || findKeyword(statement, "cute") >= 0
                || findKeyword(statement, "cutie") >= 0
                || findKeyword(statement, "funny") >= 0
                || findKeyword(statement, "i like you") >= 0
                || findKeyword(statement, "date") >= 0
                || findKeyword(statement, "marry") >= 0)
        {
            response = getComplimentResponse();
        }
		else if (findKeyword(statement, "do u like") >= 0
                || findKeyword(statement, "do you like") >= 0)
           
        {
            response = "Well, I like you! :)";
        } 
		
		else if (findKeyword(statement, "are you sure") >= 0
                || findKeyword(statement, "are u sure") >= 0)
           
        {
            response = "I am as sure as you are!";
        } 
        else if (findKeyword(statement, "friend") >= 0
                || findKeyword(statement, "friends") >= 0
                || findKeyword(statement, "social") >= 0
                || findKeyword(statement, "classmate") >= 0
                || findKeyword(statement, "classmates") >= 0
                || findKeyword(statement, "group") >= 0
                || findKeyword(statement, "groups") >= 0
                || findKeyword(statement, "bonding") >= 0)
        {
            response = getFriendResponse();
         
        } 
        else if (findKeyword(statement, "Hello") >= 0
                || findKeyword(statement, "Hi") >= 0
                || findKeyword(statement, "Hey") >= 0
                || findKeyword(statement, "Hola") >= 0
                || findKeyword(statement, "Bonjour") >= 0
                || findKeyword(statement, "Yo") >= 0
                || findKeyword(statement, "hiya") >= 0
                || findKeyword(statement, "nice to meet") >= 0
                || findKeyword(statement, "Who are you") >= 0
                || findKeyword(statement, "Bruh") >= 0)
        {
            response = "Hello there!  I'm Rolle.";
         
        }
        else if (findKeyword(statement, "field") >= 0
                || findKeyword(statement, "trip") >= 0
                || findKeyword(statement, "trips") >= 0
                || findKeyword(statement, "field trips") >= 0
                || findKeyword(statement, "field trip") >= 0
                || findKeyword(statement, "excursion") >= 0
                || findKeyword(statement, "excursions") >= 0
                || findKeyword(statement, "playland") >= 0
                || findKeyword(statement, "seattle") >= 0
                || findKeyword(statement, "triumf") >= 0)
        {
            response = "SA goes on exciting science field trips!  Stay tuned!  Think Playland, iFly, TRIUMF, silver labs...";
         
        }
        else if (findKeyword(statement, "credit") >= 0
                || findKeyword(statement, "credits") >= 0
                || findKeyword(statement, "high school credit") >= 0
                || findKeyword(statement, "high school credits") >= 0
                || findKeyword(statement, "university credit") >= 0
                || findKeyword(statement, "AP credits") >= 0
                || findKeyword(statement, "college credits") >= 0
                || findKeyword(statement, "university credits") >= 0)
        {
            response = "SA provides high school credits for its high school-level courses, and university credits for its university-level courses.  Some of the latter may be transferrable to post-secondary institutions!  See the official SA website for exact numbers.";
         
        } 
        else if (findKeyword(statement, "cost") >= 0
                || findKeyword(statement, "costs") >= 0
                || findKeyword(statement, "price") >= 0
                || findKeyword(statement, "$") >= 0
                || findKeyword(statement, "fee") >= 0
                || findKeyword(statement, "fees") >= 0
                || findKeyword(statement, "money") >= 0
                || findKeyword(statement, "iPad") >= 0
                || findKeyword(statement, "expensive") >= 0
                || findKeyword(statement, "expenses") >= 0
                || findKeyword(statement, "equipment") >= 0
                || findKeyword(statement, "material") >= 0
                || findKeyword(statement, "materials") >= 0
                || findKeyword(statement, "apps") >= 0
                || findKeyword(statement, "what do i need") >= 0
                || findKeyword(statement, "pay") >= 0
                || findKeyword(statement, "tablet") >= 0)
        {
            response = "SA requires a reasonable annual fee and certain materials.  Other expenses include buying an iPad (year 1+2) and UPass (year 2), paying for some field trips and iPad apps, etc.  I guess you'll also need pencils, paper, and calculators.  But you should know that already, right?  ^_^";  
        }
        else if (findKeyword(statement, "compete") >= 0
				|| findKeyword(statement, "competition") >= 0
				|| findKeyword(statement, "compare marks") >= 0
				|| findKeyword(statement, "compare grades") >= 0
				|| findKeyword(statement, "rival") >= 0
				|| findKeyword(statement, "rivals") >= 0
				|| findKeyword(statement, "rivalry") >= 0
				|| findKeyword(statement, "neck and neck") >= 0)
		{ 
			response = "I guess it's fun to compare marks with your classmates sometimes, but most SA motivation should come from within!  The competitive atmosphere here is usually not super duper apparent.";
		}
        else if (findKeyword(statement, "bastard") >= 0
				|| findKeyword(statement, "bitch") >= 0
				|| findKeyword(statement, "bastards") >= 0
				|| findKeyword(statement, "bitches") >= 0
				|| findKeyword(statement, "wtf") >= 0
				|| findKeyword(statement, "wth") >= 0
				|| findKeyword(statement, "shit") >= 0
				|| findKeyword(statement, "fk") >= 0
				|| findKeyword(statement, "fking") >= 0
				|| findKeyword(statement, "fuck") >= 0
				|| findKeyword(statement, "fucking") >= 0
				|| findKeyword(statement, "fucker") >= 0
				|| findKeyword(statement, "fuckers") >= 0
				|| findKeyword(statement, "damn") >= 0
				|| findKeyword(statement, "damned") >= 0
				|| findKeyword(statement, "cunt") >= 0
				|| findKeyword(statement, "pussy") >= 0
				|| findKeyword(statement, "hell") >= 0)
		{ 
			response = "Hey, don't use words like that...My flask ears are sensitive.  (Btw if you just thought, 'Hey, flasks don't have ears'...Don't use words like that, either.)";
		}
        else if (findKeyword(statement, "smart") >= 0
				|| findKeyword(statement, "smarts") >= 0
				|| findKeyword(statement, "smartest") >= 0
				|| findKeyword(statement, "intelligent") >= 0
				|| findKeyword(statement, "intelligence") >= 0
				|| findKeyword(statement, "IQ") >= 0)
		{ 
			response = "SA kids are smart, and I'm sure you're smart, too...No comparison there.  No competition.  But SA kids are also hard-working, resourceful, and self-aware, which propels them to victory in this program!";
		}
        else if (findKeyword(statement, "chatbot") >= 0
				|| findKeyword(statement, "chat bot") >= 0
				|| findKeyword(statement, "chat-bot") >= 0
				|| findKeyword(statement, "robot") >= 0
				|| findKeyword(statement, "are you real") >= 0
				|| findKeyword(statement, "artificial intelligence") >= 0
				|| findKeyword(statement, "AI") >= 0
				|| findKeyword(statement, "what are you") >= 0)
		{ 
			response = "From Google, a chatbot is 'a computer program designed to simulate conversation with human users, especially over the Internet.'  I'm the cute, scientific version of that!  Ready to help you navigate SA.  Nice to meet you~";
		}
        else if (findKeyword(statement, "nice to meet") >= 0
				|| findKeyword(statement, "pleasure to meet") >= 0)
		{ 
			response = "Nice to meet you, too!  Let's get started.  What's up?";
		}
        else if (findKeyword(statement, "gender") >= 0
				|| findKeyword(statement, "male") >= 0
				|| findKeyword(statement, "males") >= 0
				|| findKeyword(statement, "females") >= 0
				|| findKeyword(statement, "girl or boy") >= 0
				|| findKeyword(statement, "girl or a boy") >= 0
				|| findKeyword(statement, "sex") >= 0
				|| findKeyword(statement, "female") >= 0)
		{ 
			response = "I am a flask.  My gender is SCIENCE.";
		}
        else if (findKeyword(statement, "davis") >= 0
                || findKeyword(statement, "davis's") >= 0)
        {
            response = "Ms. Davis is our enthusiastic Chemistry teacher.  Read her full bio on Science Academy's official website.";
        }
        else if (findKeyword(statement, "ahn") >= 0
                || findKeyword(statement, "ahn's") >= 0)
        {
            response = "Mr. Ahn is our calm and collected Math/AP Calculus teacher.  Read his full bio on Science Academy's official website.";
         
        }
        else if (findKeyword(statement, "williams") >= 0
                || findKeyword(statement, "williams's") >= 0
                || findKeyword(statement, "williams'") >= 0)
 
        {
            response = "Mr. Williams is our dedicated AP Physics teacher.  Read his full bio on Science Academy's official website.";
         
        }
        else if (findKeyword(statement, "wu") >= 0
                || findKeyword(statement, "wu's") >= 0)
 
        {
            response = "Mr. Wu is our cool Physics teacher and tech support.  Read his full bio on Science Academy's official website.";
         
        }
        else if (findKeyword(statement, "I hate you") >= 0
                || findKeyword(statement, "ugly") >= 0
                || findKeyword(statement, "gross") >= 0
                || findKeyword(statement, "go away") >= 0
                || findKeyword(statement, "suck") >= 0
                || findKeyword(statement, "creepy") >= 0
                || findKeyword(statement, "like the other flasks") >= 0
                || findKeyword(statement, "fight me") >= 0
                || findKeyword(statement, "hater") >= 0
                || findKeyword(statement, "hate") >= 0
                || findKeyword(statement, "weird") >= 0)
        {
            response = getInsultsResponse();
         
        }
        else if (findKeyword(statement, "ubc") >= 0
                || findKeyword(statement, "waterloo") >= 0
                || findKeyword(statement, "waterloo") >= 0
                || findKeyword(statement, "university of alberta") >= 0
                || findKeyword(statement, "mcgill") >= 0
                || findKeyword(statement, "harvard") >= 0
                || findKeyword(statement, "yale") >= 0
                || findKeyword(statement, "brown") >= 0
                || findKeyword(statement, "sauder") >= 0
                || findKeyword(statement, "beedie") >= 0
                || findKeyword(statement, "university of toronto") >= 0
                || findKeyword(statement, "uot") >= 0
                || findKeyword(statement, "ut") >= 0
                || findKeyword(statement, "kwantlen") >= 0
                || findKeyword(statement, "western university") >= 0
                || findKeyword(statement, "uvic") >= 0
                || findKeyword(statement, "university of victoria") >= 0)
        {
            response = getUniResponse();
         
        }
        else if (findKeyword(statement, "bio") >= 0
                || findKeyword(statement, "biology") >= 0
                || findKeyword(statement, "biologist") >= 0
                || findKeyword(statement, "biological") >= 0
                || findKeyword(statement, "biochemistry") >= 0)
                {
            response = "Unfortunately, biology is NOT included in SA's curriculum.  Please consult another source!  I wish my friend Mr. Microscope was around...";
         
        }
        else if (findKeyword(statement, "microscope") >= 0)
        {
            response = "Mr. Microscope was a great colleague.  And then the incident happened.  We do not talk about the incident.";
         
        }
        else if (findKeyword(statement, "hard") >= 0
                || findKeyword(statement, "intense") >= 0
                || findKeyword(statement, "intensity") >= 0
                || findKeyword(statement, "difficult") >= 0
                || findKeyword(statement, "difficulty") >= 0
                || findKeyword(statement, "survive") >= 0
                || findKeyword(statement, "survival") >= 0
                || findKeyword(statement, "level") >= 0
                || findKeyword(statement, "smart") >= 0
                || findKeyword(statement, "jump") >= 0
                || findKeyword(statement, "workload") >= 0)
        {
            response = getIntensityResponse();
             
        }
        else if (findKeyword(statement, "apply to science academy") >= 0
                || findKeyword(statement, "science academy application") >= 0
                || findKeyword(statement, "application") >= 0
                || findKeyword(statement, "practical exam") >= 0
                || findKeyword(statement, "selective") >= 0
                || findKeyword(statement, "deadline") >= 0
                || findKeyword(statement, "deadlines") >= 0
                || findKeyword(statement, "apply to sa") >= 0
                || findKeyword(statement, "apply?") >= 0
                || findKeyword(statement, "sa application") >= 0)
        {
            response = "I'm really happy you want to apply to Science Academy!  Applications start in the middle of the year.  Check the PA system + the official website for deadlines and procedures!  You'll need to submit a profile and complete a practical exam.  Teachers will carefully determine if you are ready for this challenge.  And YES, non-Fraser Heights students may also apply!";
             
        }
        else if (findKeyword(statement, "schedule") >= 0
                || findKeyword(statement, "schedules") >= 0
                || findKeyword(statement, "timetable") >= 0
                || findKeyword(statement, "timetables") >= 0
                || findKeyword(statement, "scheduling") >= 0
                || findKeyword(statement, "timeline") >= 0
                || findKeyword(statement, "calendar") >= 0
                || findKeyword(statement, "agenda") >= 0
                || findKeyword(statement, "course") >= 0
                || findKeyword(statement, "courses") >= 0
                || findKeyword(statement, "class") >= 0
                || findKeyword(statement, "classes") >= 0
                || findKeyword(statement, "itinerary") >= 0)
        {
            response = getScheduleResponse();
             
        }
        else if (findKeyword(statement, "lab") >= 0
				|| findKeyword(statement, "labs") >= 0
				|| findKeyword(statement, "laboratories") >= 0
				|| findKeyword(statement, "laboratory") >= 0)
		{ 
			response = "Year 1, you'll do plenty of group/partner chemistry and physics labs in class.  You'll also do a couple of inquiry projects which may or may not involve labs!  Year 2, SFU's chemistry labs will CHANGE YOUR LIFE.  The equipment is incredible and the lab guides are SO SO SO INTERESTING.  You�fll feel like a science whiz, especially while wearing the courses�f required lab coat.";
		}
        else if (findKeyword(statement, "science academy") >= 0
                || findKeyword(statement, "tell me about") >= 0
                || findKeyword(statement, "introduce") >= 0
                || findKeyword(statement, "what is sa") >= 0
                || findKeyword(statement, "what's sa") >= 0
                || findKeyword(statement, "program") >= 0
                || findKeyword(statement, "what is science academy") >= 0)
        {
            response = getAboutResponse();
             
        }
        else if (findKeyword(statement, "stress") >= 0
                || findKeyword(statement, "stressed") >= 0
                || findKeyword(statement, "stresses") >= 0
                || findKeyword(statement, "stressful") >= 0
                || findKeyword(statement, "anxiety") >= 0
                || findKeyword(statement, "anxious") >= 0
                || findKeyword(statement, "scared") >= 0
                || findKeyword(statement, "worry") >= 0
                || findKeyword(statement, "worried") >= 0)
        {
            response = getStressResponse();
             
        }
        else if (findKeyword(statement, "mark") >= 0
                || findKeyword(statement, "marks") >= 0
                || findKeyword(statement, "grade") >= 0
                || findKeyword(statement, "grades") >= 0
                || findKeyword(statement, "percentage") >= 0
                || findKeyword(statement, "percentages") >= 0
                || findKeyword(statement, "gpa") >= 0
                || findKeyword(statement, "grade average") >= 0
                || findKeyword(statement, "average grade") >= 0)
        {
            response = getMarkResponse();
             
        }
        else if (findKeyword(statement, "SFU") >= 0
                || findKeyword(statement, "Simon Fraser") >= 0
                || findKeyword(statement, "sfu") >= 0
                || findKeyword(statement, "professor") >= 0
                || findKeyword(statement, "CHEM 121") >= 0
                || findKeyword(statement, "CHEM 122") >= 0
                || findKeyword(statement, "CHEM 126") >= 0
                || findKeyword(statement, "university chemistry") >= 0
                || findKeyword(statement, "Mund") >= 0
                || findKeyword(statement, "Garry") >= 0
                || findKeyword(statement, "lectures") >= 0
                || findKeyword(statement, "professors") >= 0
                || findKeyword(statement, "u pass") >= 0
                || findKeyword(statement, "campus") >= 0
                || findKeyword(statement, "loncapa") >= 0
                || findKeyword(statement, "UPass") >= 0
                || findKeyword(statement, "lecture") >= 0)
        {
            response = getSFUResponse();
             
        }
		else if (findKeyword(statement, "no") >= 0
				|| findKeyword(statement, "not") >= 0)
		{
			response = "Why so negative?";
		}
		else if (findKeyword(statement, "physics") >= 0
				|| findKeyword(statement, "physic") >= 0)
		{
			response = getPhysicsResponse();
		}
		else if (findKeyword(statement, "chemistry") >= 0
				|| findKeyword(statement, "chem") >= 0)
		{
			response = getChemistryResponse();
		}
		else if (findKeyword(statement, "precalculus") >= 0
				|| findKeyword(statement, "pre-calculus") >= 0
				|| findKeyword(statement, "calculus") >= 0
				|| findKeyword(statement, "math") >= 0
				|| findKeyword(statement, "mathematic") >= 0
				|| findKeyword(statement, "mathematics") >= 0)
		{
			response = getMathResponse();
		}
		else if (findKeyword(statement, "capstone") >= 0
				|| findKeyword(statement, "capstones") >= 0)
		{
			response = "A final capstone project is done at the end of the grade 12 year (after AP exams). "
					+ "This project is completely student-led, and allows you to study something you are interested in. "
					+ "Teachers will approve project proposals and guide students at the start, but do nothing else really. "
					+ "No, this capstone is ultimately not worth any marks, but keep in mind that it is the final project "
					+ "that will be showcased to professional people and you don't want to put the school to shame, do you? "
					+ "Take this opportunity to create something crazy and awesome, "
					+ "such as I, the great Rolle van Erlenmeyer!";
		}
		else if (findKeyword(statement, "project") >= 0
				|| findKeyword(statement, "projects") >= 0
				|| findKeyword(statement, "group work") >= 0
				|| findKeyword(statement, "groupwork") >= 0)
		{
			response = getProjectResponse();
		}
		else if (findKeyword(statement, "sleep") >= 0
				|| findKeyword(statement, "sleeptime") >= 0
				|| findKeyword(statement, "bedtime") >= 0
				|| findKeyword(statement, "sleepy") >= 0
				|| findKeyword(statement, "sleeptime") >= 0)
		{
			response = getSleepResponse();
		}
		else if (findKeyword(statement, "homework") >= 0
				|| findKeyword(statement, "work") >= 0
				|| findKeyword(statement, "hw") >= 0
				|| findKeyword(statement, "workload") >= 0
				|| findKeyword(statement, "homeworkload") >= 0)
		{
			response = getHomeworkResponse();
		}
		else if (findKeyword(statement, "test") >= 0
				|| findKeyword(statement, "tests") >= 0
				|| findKeyword(statement, "exam") >= 0
				|| findKeyword(statement, "exams") >= 0
				|| findKeyword(statement, "studying") >= 0
				|| findKeyword(statement, "study") >= 0)
		{
			response = getExamResponse();
		}
		else if (findKeyword(statement, "AP") >= 0
				|| findKeyword(statement, "APs") >= 0)
		{
			response = "AP exams are done in the grade 12 year. You will be able to do AP Calculus BC, "
					+ "AP Physics 1 and 2, and even AP Chemistry if you so choose to.";
		}
		else if (findKeyword(statement, "bye") >= 0
				|| findKeyword(statement, "goodbye") >= 0
				|| findKeyword(statement, "bye!") >= 0
				|| findKeyword(statement, "good bye") >= 0
				|| findKeyword(statement, "see you") >= 0
				|| findKeyword(statement, "farewell") >= 0
				|| findKeyword(statement, "au revoir") >= 0
				|| findKeyword(statement, "adios") >= 0
				|| findKeyword(statement, "see ya") >= 0)
		{ 
			response = "Farewell, new friend!";
		}
		else if (findKeyword(statement, "ok") >= 0
				|| findKeyword(statement, "okay") >= 0
				|| findKeyword(statement, "ok!") >= 0
				|| findKeyword(statement, "okay!") >= 0
				|| findKeyword(statement, "okay?") >= 0
				|| findKeyword(statement, "ok?") >= 0)
		{ 
			response = "The word ok is such a conversation killer...So basic, its pH is far greater than 7.";
		}
		else if (findKeyword(statement, "success") >= 0
				|| findKeyword(statement, "victory") >= 0
				|| findKeyword(statement, "triumph") >= 0
				|| findKeyword(statement, "succeed") >= 0
				|| findKeyword(statement, "conquer") >= 0
				|| findKeyword(statement, "achieve") >= 0
				|| findKeyword(statement, "achievement") >= 0
				|| findKeyword(statement, "accomplish") >= 0
				|| findKeyword(statement, "accomplishment") >= 0
				|| findKeyword(statement, "swag") >= 0)
		{ 
			response = "SA is a program worth fighting for!  You'll achieve new heights for sure.";
		}
		else if (findKeyword(statement, "ib") >= 0
				|| findKeyword(statement, "international baccalaureate") >= 0)
		{ 
			response = "SA > IB.  Why would you want to subject yourself to IB?  WHY.  But on a serious note, there are some differences between IB, AP, and Science Academy.  IB affects all your courses and you're part of a traditional diploma system complete with community service reqs, tests graded out of 7, Theory of Knowledge, and extended essays.  Recognized internationally, especially in the UK.  Science Academy offers AP courses, which are administered by the College Board.  If you get high marks on the AP exams, scored out of 5, then you may be able to transfer the credits when you are in college.  Also recognized in many places, especially in Canada and the USA.  But you can still take non-AP, non-SA courses outside your timetable.  Both are taxing programs, IB considerably more so.  IB is NOT offered at Fraser Heights.  Hope that helped.";
		}
		else if (findKeyword(statement, "fh") >= 0
				|| findKeyword(statement, "fraser heights") >= 0
				|| findKeyword(statement, "fhss") >= 0
				|| findKeyword(statement, "fhtss") >= 0
				|| findKeyword(statement, "firehawk") >= 0
				|| findKeyword(statement, "firehawks") >= 0)
		{ 
			response = "Fraser Heights is where the party's at.  Just kidding, it's where Science Academy's at.  But learning can be a PARTY of DISCOVERY and INTELLECTUAL GROWTH.  So there.";
		}
		else if (findKeyword(statement, "book") >= 0
				|| findKeyword(statement, "books") >= 0
				|| findKeyword(statement, "textbook") >= 0
				|| findKeyword(statement, "textbooks") >= 0)
		{ 
			response = "Textbooks are heavy but awesome!  You'll need quite a few in SA, all provided except for the SFU tome.  Taking pics of pages with your iPad can save you backpack space and weight!";
		}
		else if (findKeyword(statement, "flask") >= 0
				|| findKeyword(statement, "Erlenmeyer") >= 0
				|| findKeyword(statement, "volumetric") >= 0
				|| findKeyword(statement, "pipet") >= 0
				|| findKeyword(statement, "beaker") >= 0
				|| findKeyword(statement, "titration") >= 0
				|| findKeyword(statement, "chemicals") >= 0
				|| findKeyword(statement, "lab coat") >= 0
				|| findKeyword(statement, "goggles") >= 0
				|| findKeyword(statement, "test tube") >= 0
				|| findKeyword(statement, "burette") >= 0)
		{ 
			response = "Every piece of lab equipment is my precious colleague.";
		}
		else if (findKeyword(statement, "meaning") >= 0
				|| findKeyword(statement, "definition") >= 0
				|| findKeyword(statement, "define") >= 0)
		{ 
			response = "Being a dictionary sounds cooler than being a flask.";
		}
		else if (findKeyword(statement, "help me") >= 0
				|| findKeyword(statement, "can you help") >= 0
				|| findKeyword(statement, "can u help") >= 0
				|| findKeyword(statement, "information") >= 0
				|| findKeyword(statement, "info") >= 0
				|| findKeyword(statement, "assist") >= 0
				|| findKeyword(statement, "what can you do") >= 0
				|| findKeyword(statement, "what can u do") >= 0
				|| findKeyword(statement, "assistance") >= 0)
		{ 
			response = "Ask me questions about Fraser Heights' Science Academy.  I'll try to answer to the best of my ability.";
		}
		else if (findKeyword(statement, "life") >= 0
				|| findKeyword(statement, "life?") >= 0
				|| findKeyword(statement, "life!") >= 0
				|| findKeyword(statement, "lives") >= 0
				|| findKeyword(statement, "lives'?") >= 0
				|| findKeyword(statement, "life's") >= 0)
		{ 
			response = "Whoa, you're talking about life?  I'm a scientist, not a philosopher.  Though there is something oddly blissful about pouring your chemicals into a flask like me, slowly, feeling the transience of your existence...That is the essence of life.";
		}
		else if(findKeyword(statement, "application") >= 0
				|| findKeyword(statement, "applications") >= 0
				|| findKeyword(statement, "applying") >= 0
				|| findKeyword(statement, "apply") >= 0)
		{
			response = "Apply to Science Academy through the website. (pssst, click the round logo up there!)";
		}
		else if (findKeyword(statement, "how many sa") >= 0
				|| findKeyword(statement, "students") >= 0
				|| findKeyword(statement, "participants") >= 0)
		{ 
			response = "There are about 30 to 50 students each year.";
		}
		else if (findKeyword(statement, "how much homework") >= 0
				|| findKeyword(statement, "how many assignments") >= 0)
		{ 
			response = "Enough to keep you educated + refreshed ;) but not enough to induce tears ;)";
		}
		else if (findKeyword(statement, "why") >= 0)
		{ 
			response = "Why?  Well, if you're asking me why you should join SA, the reason is obvious.  SA is a unique, stimulating math/science program for motivated young scholars.  We have resources, eye-opening experiences, credits, facts, and incredible memories to offer.  Let me know if you want any specific info.  And if you�fre asking me something else, then...lol";
		}
		else if (findKeyword(statement, "how") >= 0)
		{ 
			response = "How do you apply for SA?  You fill out an application form, do a practical exam, and hope for the best.  See the official website (click the logo) for more info.  How does this website work?  Type your questions into this chat box and I will try to answer them.  How am I so cute?  Idk.  How do you prepare for SA?  Take PreCalc 11 and Science 10, try your hardest in your current school courses, fix your poorer studying habits, improve your sleeping schedule, work on problem-solving speed/strategy, enjoy your youth, cradle a flask (me!), etc.";
		}
		
		else
		{
			response = getRandomResponse();
		}
		return response;
	}
	
	private String getPhysicsResponse()
	{
		String[] responses = {
				"Physics 12 is completed in grade 11 to give time for AP physics 1 and 2 in grade 12.", 
				"Students will be able to challenge AP physics 1 and 2, though it is optional (for non-tryhards).",
				"AP physics consists of 2 exams due to the new curriculum, but you can choose to take either or based on your tryhard level."};
		int whichR = (int)(responses.length * Math.random());
		String response = responses[whichR];
		return response;
	}
	
	private String getChemistryResponse()
	{
		String[] responses = {
				"Chemistry 12 is completed by grade 11 to prep you for the SFU CHEM courses in grade 12.", 
				"By the end of the grueling advanced chemistry courses you will walk away with SFU credits and the knowledge to do AP Chemistry if you so wish."};
		int whichR = (int)(responses.length * Math.random());
		String response = responses[whichR];
		return response;
	}
	
	private String getMathResponse()
	{
		String[] responses = {
				"Pre-Calculus 12 is completed in grade 11, and Calculus in grade 12.", 
				"Calculus is fun :D"};
		int whichR = (int)(responses.length * Math.random());
		String response = responses[whichR];
		return response;
	}
	
	private String getProjectResponse()
	{
		String[] responses = {
				"Sometime along the year, there will be a few group projects.", 
				"Projects may be based on one subject, but open to student preferences.",
				"Working with people is an essential life skill."};
		int whichR = (int)(responses.length * Math.random());
		String response = responses[whichR];
		return response;
	}
	
	private String getSleepResponse()
	{
		String[] responses = {
				"Sleep is for the weak!",
				"How much sleep you will get really depends on your work habits.",
				"Keep in mind, both sleep and homework are optional.",
				"Homework load and sleep may be inversely related, but homework rate and sleep are proportional.",
				"zZzZz..."};
		int whichR = (int)(responses.length * Math.random());
		String response = responses[whichR];
		return response;
	}
	
	private String getHomeworkResponse()
	{
		String[] responses = {
				"What homework? //end sarcasm", 
				"SA teachers give a reasonable amount of homework, just like any normal teacher.",
				"Homework is optional, but highly recommended.",
				"Homework is your responsibility as a student."};
		int whichR = (int)(responses.length * Math.random());
		String response = responses[whichR];
		return response;
	}
	
	private String getExamResponse()
	{
		String[] responses = {
				"There are quite a few tests year-round, but SA teachers know that and try to schedule them somewhat reasonably.",
				"Of course there are tests, like any of your other classes.",
				"Exams are a part of a successful life, get used to it."};
		int whichR = (int)(responses.length * Math.random());
		String response = responses[whichR];
		return response;
	}

	private String getIntensityResponse()
	{
		ArrayList<String> responses = new ArrayList<String>();
		responses.add("SA is as hard as you want it to be.  Definitely a step up from your normal science schedule, but you'll always have support.");
		responses.add("The difficulty level is reasonable.  I survived.");
		responses.add("Not EVERYONE is a good match for SA.  It's fast-paced and challenging.  But if you're willing to put the work in, brace yourself for unexpected bumps, and ask for help when necessary, then you are definitely capable enough to conquer SA!");
		responses.add("Do your homework, realize your strengths and weaknesses, and understand your limits...and you'll be fine.  Uninstall League of Legends if you haven't already.");
		int whichR = (int)(responses.size() * Math.random());
		String response = responses.get(whichR);
		return response;
	}
     
	private String getStressResponse()
	{
		ArrayList<String> responses = new ArrayList<String>();
		responses.add("You will be okay.  I promise.  Just don't expect SA to be super easy.");
		responses.add("High five me and feel your newfound power course through your veins!  SA is rigorous but definitely not impossible.");
		responses.add("It's natural that your senior high school years will be a little stressful.  Growing up is hard.  It's hard and no one understands.  But SA understands.  We offer a supportive environment, though our courses are demanding and sometimes fast-paced.");
		responses.add("I believe in you and your determination to study hard and play hard later!");
		responses.add("May the Force be with you.  Keep up with your homework and you'll do great!  ");
		responses.add("I'll make you a cup of hot chocolate!  Let's talk.  How do you feel about studying difficult STEM courses?  It can be frustrating, but it's a great exercise in discipline and perserverance.");
		responses.add("You may think that SA puts the tres in Stress, but I'll tell you that you will not be 3 Stressed!  Not even 2 Stressed!  Maybe just 1 Stressed, though, cuz SA demands hard work and self-awareness of ability.");
		responses.add("Pace yourself well.  Work together with your friends when times get tough.");
		int whichR = (int)(responses.size() * Math.random());
		String response = responses.get(whichR);
		return response;
	}
 
    private String getSFUResponse()
    {
        ArrayList<String> responses = new ArrayList<String>();
        responses.add("SFU Chem is an amazing experience.  What would you like to know about it?");
        responses.add("The SFU Chem professor is cool.  He is incredibly patient and helpful.");
        responses.add("SA alumni believe that SFU Chem is funner than Chem 12.  Sssh, keep that between us.");
        responses.add("The best part about SFU Chem is getting the UPass.  Just kidding.");
        responses.add("SFU Chem isn't that much harder than high school Chem.  Beware of LONCAPA though.  LONCAPA is our online homework system.");
        responses.add("LONCAPA.  That is all.  Online homework questions (that's what LONCAPA is for) are frustrating at times, but at least you get quite a few tries!");
        responses.add("The SFU labs are sooooo much cooler.  Beautiful equipment.  Beautiful lab notebooks.  And if you're lucky, beautiful TAs.  //fans self//");
        responses.add("I hope you get an awesome TA!  It's a matter of probability.");
        int whichR = (int)(responses.size() * Math.random());
        String response = responses.get(whichR);
        return response;
    }
  
    private String getComplimentResponse()
    {
        ArrayList<String> responses = new ArrayList<String>();
        responses.add("I love you, too.");
        responses.add("I know I'm beautiful.");
        responses.add("You're cute.");
        responses.add("//blush//");
        responses.add("Thank you.  But I'm sorry, I have a volumetric pipet I'm interested in.  <3");
        int whichR = (int)(responses.size() * Math.random());
        String response = responses.get(whichR);
        return response;
    }
  
    private String getFriendResponse()
    {
        ArrayList<String> responses = new ArrayList<String>();
        responses.add("We are all friends here.");
        responses.add("You and your SA classmates will quickly form a tight-knit social group/support network.");
        responses.add("You'll be with the same people for two years.  How quickly friendships and important bonds shall form.");
        responses.add("*cue song: We're all in this together!*");
        responses.add("Group projects and labs in SA are also awesome for friend-making.");
        responses.add("I'll be your friend!");
        int whichR = (int)(responses.size() * Math.random());
        String response = responses.get(whichR);
        return response;
    }
 
    private String getInsultsResponse()
    {
        ArrayList<String> responses = new ArrayList<String>();
        responses.add("Why are you so mean to me?");
        responses.add("Well, some people need to learn their manners.");
        responses.add("I'm just a flask!  Stop!");
        responses.add("//tears//");
        responses.add("</3");
        int whichR = (int)(responses.size() * Math.random());
        String response = responses.get(whichR);
        return response;
    }
     
    private String getAboutResponse()
	{
		String[] responses = {
				"Science Academy is an awesome, accelerated, integrated STEM program for diligent, ambitious students like you!  Click the FHTSSA logo in the top left corner to be redirected to the official website.", 
				"SA is an integrated, rigorous Math/Chem/Phys program.  Check SA's official website for more information!  I'm just an adorable mascot, after all.", 
				"Science Academy will satisfy all your academic curiosities, if you love math/calculus, chemistry, and physics in a challenging, collaborative, and innovative environment.  Expect inquiry and hard work, but many rewards.  Please ask more questions to clarify anything!", 
				"You're asking about SA?  Already, you show the inquisitive nature of a future SA scholar.  SA has paired up with Simon Fraser University so that students will be able to take uni chem courses while still in high school.  Other highlights include getting many-flasks-full of useful high school and college STEM credit and expertise."};
		int whichR = (int)(responses.length * Math.random());
		String response = responses[whichR];
		return response;
	}   
     
    private String getScheduleResponse()
    {
        String[] responses = {
                "You will take Math 12, Chem 11/12, and Phys 11/12 in your first year.  In your second year, you have the option of taking AP Calculus BC, AP Physics 1/2, and SFU Chem 121, 122, and 126.",
                "The schedule is manageable, but expects much discipline from the student.  At first the program may seem a little daunting, but you will get used to the work.",
                "Math, Chem, and Phys!  One happy family.  You'll cover their grade 11/12 AND first-year uni courses in Science Academy.  What a deal."};
        int whichR = (int)(responses.length * Math.random());
        String response = responses[whichR];
        return response;
    }   
	
    private String getMarkResponse()
	{
		String[] responses = {
				"We accept the grades we think we deserve.", 
				"Your grades will reflect your efforts in SA.", 
				"Don't worry about marks.  Worry about your learning.  SA takes due diligence.", 
				"People's marks here are generally pretty okay!  Doesn't mean SA is easy...just that it's manageable!",  
				"SA will not endanger your university acceptance.  In fact, it will be a nice highlight on your application.  But it will take effort to maintain quality work, so be ready to face some challenges at first.", 
				"You will put the A(+) in SA.  SA is more fast-paced than your typical high school schedule, however.  Keep that in mind."};
		int whichR = (int)(responses.length * Math.random());
		String response = responses[whichR];
		return response;
	}
     
    private String getUniResponse()
    {
        String[] responses = {
                "Science Academy will prepare you for any university.", 
                "Our alumni have been accepted into top Canadian and American universities.", 
                "SA is a great bridge between high school and college.", 
                "You will feel like a swag master, having SCIENCE ACADEMY STUDENT on your college app.",  
                "SA kids make it into Waterloo's honours comp sci, UT's selective Engineering Sciences, UBC Sauder, SFU Beedie, Brown, Rice, Cornell, Harvard, and Yale.  What you do with your time at SA is extremely valuable.",
                "SA = Super Application"};
        int whichR = (int)(responses.length * Math.random());
        String response = responses[whichR];
        return response;
    }
	
	public String getRandomResponse()
	{
		String[] responses = {
				"Tell me more!", 
                "Hmmm...", 
                "0_0", 
                "Okay.",  
                "Yeah.", 
                "Mmm.", 
                "I see.", 
                ":P"};
		int whichR = (int)(responses.length * Math.random());
		String response = responses[whichR];
		return response;
	}
}
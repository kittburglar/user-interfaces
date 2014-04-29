#include "MainPanel.h"
#include "TurtleGraphics.h"
#include "Button.h"
#include "Slider.h"
#include "Label.h"
#include "A2WidgetIdentifiers.h"
#include "Logging.h"
#include "Timer.h"
#include "XApplication.h"
using namespace cs349;

MainPanel::MainPanel()
  : Panel(MAIN_PANEL_ID)
{
  // TODO CS349: Create your entire interface here. You will need to
  // modify the provided code below to lay things out the way you like

  // Note the use of unique identifiers for each widget when they are
  // constructed. See A2WidgetIdentifiers.h and the assignment
  // specification for the identifiers you should use for each of the
  // components you create. These identifiers are used for unit tests
  // to easily identify components in the interactor tree.
  
  Component* turtleGraphicsPanel = new Panel(TURTLE_GRAPHICS_PANEL_ID);
  Component* playbackPanel = new Panel(PLAYBACK_PANEL_ID);
  Component* stepPanel = new Panel(CURRENT_TURTLE_STEP_PANEL_ID);
  Component* buttonPanel = new Panel(BUTTON_PANEL_ID);
  TurtleGraphics* const turtle  = new TurtleGraphics(TURTLE_GRAPHICS_WIDGET_ID);
  Slider* playbackSlider = new Slider(PLAYBACK_SLIDER_ID, Slider::HORIZONTAL_SLIDER);
  Slider* stepSlider = new Slider(CURRENT_TURTLE_STEP_SLIDER_ID, Slider::HORIZONTAL_SLIDER);
  Button* startButton = new Button(GO_TO_START_BUTTON_ID, "Start");
  Button* prevButton = new Button(PREVIOUS_FRAME_BUTTON_ID, "Previous");
  Button* playButton = new Button(PLAY_BUTTON_ID, "Play");
  Button* nextButton = new Button(NEXT_FRAME_BUTTON_ID, "Next");
  Button* endButton = new Button(GO_TO_END_BUTTON_ID, "End");
  Label* turtleLabel = new Label(TURTLE_GRAPHICS_OUTPUT_LABEL_ID, "Turtle Graphics Output");
  Label* playbackLabel = new Label(PLAYBACK_OUTPUT_LABEL_ID, "Playback Rate:");
  Label* stepLabel = new Label(CURRENT_TURTLE_STEP_LABEL_ID, "Current Turtle Step:");
  Label* playbackLowLabel = new Label("PLAYBACK_LOW_LABEL", "1/10x");
  Label* playbackHighLabel = new Label("PLAYBACK_HIGH_LABEL", "10x");
  Label* stepLowLabel = new Label("STEP_LOW_LABEL", "0");
  Label* stepHighLabel = new Label("STEP_HIGH_LABEL", "200");

  this->SetBounds(Rectangle(0, 0, 500, 400));
  //TURTLEGRAPHICS
  turtleGraphicsPanel->SetBounds(Rectangle(0, 0, 250, 330));
  turtleLabel->SetBounds(Rectangle(0,300,250,30));
  turtle->SetBounds(Rectangle(0, 30, 250, 300));
  turtleGraphicsPanel->AddComponent(turtle);
  turtleGraphicsPanel->AddComponent(turtleLabel);
  this->AddComponent(turtleGraphicsPanel);
 

  //PLAYBACK SLIDER
  playbackPanel->SetBounds(Rectangle(250,0,499-250,330));
  playbackLabel->SetBounds(Rectangle(0,0,250,30));
  playbackSlider->SetBounds(Rectangle(0,30,111,40));
  playbackLowLabel->SetBounds(Rectangle(0,70,30,20));
  playbackHighLabel->SetBounds(Rectangle(90,70,20,20));
  playbackPanel->AddComponent(playbackLowLabel);
  playbackPanel->AddComponent(playbackHighLabel);
  playbackPanel->AddComponent(playbackSlider);
  playbackPanel->AddComponent(playbackLabel);
  this->AddComponent(playbackPanel);


  //CURRENT STEP SLIDER
  stepPanel->SetBounds(Rectangle(250,100,499-250,200));
  stepSlider->SetBounds(Rectangle(0,30,111,40));
  stepLabel->SetBounds(Rectangle(0,0,250,30));
  stepLowLabel->SetBounds(Rectangle(0,70,30,20));
  stepHighLabel->SetBounds(Rectangle(90,70,20,20));
  stepPanel->AddComponent(stepLabel);
  stepPanel->AddComponent(stepSlider);
  stepPanel->AddComponent(stepLowLabel);
  stepPanel->AddComponent(stepHighLabel);
  this->AddComponent(stepPanel);

  //BUTTONS
  buttonPanel->SetBounds(Rectangle(250,200,499-250,80));
  startButton->SetBounds(Rectangle(0, 0, 40, 40));
  prevButton->SetBounds(Rectangle(50, 0, 40, 40));
  playButton->SetBounds(Rectangle(100, 0, 40, 40));
  nextButton->SetBounds(Rectangle(150, 0, 40, 40));
  endButton->SetBounds(Rectangle(200, 0, 40, 40));


  
  buttonPanel->AddComponent(prevButton);
  buttonPanel->AddComponent(startButton);
  buttonPanel->AddComponent(playButton);
  buttonPanel->AddComponent(nextButton);
  buttonPanel->AddComponent(endButton);
  this->AddComponent(buttonPanel);

  playbackSlider->SetVisible(true);
  playbackPanel ->SetVisible(true);
  turtle->SetVisible(true);
  turtleGraphicsPanel->SetVisible(true);
  stepSlider->SetVisible(true);
  stepPanel->SetVisible(true);
  startButton->SetVisible(true);
  prevButton->SetVisible(true);
  playButton->SetVisible(true);
  nextButton->SetVisible(true);
  endButton->SetVisible(true);
  buttonPanel->SetVisible(true);
  turtleLabel->SetVisible(true);
  playbackLabel->SetVisible(true);
  stepLabel->SetVisible(true);
  playbackLowLabel->SetVisible(true);
  playbackHighLabel->SetVisible(true);
  stepLowLabel->SetVisible(true);
  stepHighLabel->SetVisible(true);
  //PLAYBACK
  //TimerListener timerlistener = new TimerListener();
  //Timer* timer = new Timer(XApplication::GetInstance()->GetEventQueue(), 1000, false, &timerlistener);
  //timer->Start();
  //timer->Start();
  // TODO CS349: Add other initialization code here
// TODO CS349
  //Timer* t = new Timer(XApplication::GetInstance()->GetInstance(),500,true,);
  // Some simple operations to show the turtle when starting up
  // This is an entirely random set of instructions
  // Once started up, try typing this into the console:
  // (turtle-star (find-turtle-graphics-widget) 300 23)
  // The 300 argument is the length of the edges of the star, in
  // pixels
  // The 23 argument is the number of points for the star
  //LOG_TODO << "sup";
  //ActionListener* buttonListener;
  /*
  nextButton->SetTurtleGraphics(turtle);
  
  class NextListener : public ActionListener{

    public: 
      void ActionPerformed(Button * source){

      source->GetTurtleGraphics()->GoToStep(10);
    }
  }; 
  
//turtle is a actionlistener i next
NextListener *nextlistener = new NextListener;
  nextButton->AddActionListener(nextlistener);

*/
stepSlider->SetCurValue(100);
class TimeListener : public TimerListener {
public:
  int numActionsPerformed;
  int numValuesChanged;
  string lastComponentName;

  TimeListener() {
    numActionsPerformed = 0;
    numValuesChanged = 0;
    lastComponentName = "";
  }
  virtual void ActionPerformed(Component* c) {
    if(lastComponentName == "TURTLE_GRAPHICS_WIDGET_ID"){
      LOG(INFO) << "WHOOP";
    }
    else{
    numActionsPerformed++;
    lastComponentName = c->GetName();
    }
    //LOG(INFO) << "lastComponentName";
  }
  virtual void ValueChanged(Component* c) {
    numValuesChanged++;
    lastComponentName = c->GetName();
  }
};


class EndListener : public ActionListener, public ValueListener {
public:
  int numActionsPerformed;
  int numValuesChanged;
  string lastComponentName;
  TurtleGraphics* turtle;
  Slider * slider;
  EndListener(TurtleGraphics* TG, Slider* SS) {
    numActionsPerformed = 0;
    numValuesChanged = 0;
    lastComponentName = "";
    turtle = TG;
    slider = SS;
  }

  virtual void ValueChanged(Component * c) {
    
    numActionsPerformed++;
    //astComponentName = TG->GetName();
    turtle->GoToStep(turtle->GetNumSteps());
    slider->SetCurValue(100);
    LOG(INFO) << "we heree";
  }
  virtual void ActionPerformed(Component* c) {
    
    numActionsPerformed++;
    //lastComponentName = TG->GetName();
    turtle->GoToStep(turtle->GetNumSteps());
    slider->SetCurValue(100);
    LOG(INFO) << "We here2";
  }
};

class StartListener : public ActionListener, public ValueListener {
public:
  int numActionsPerformed;
  int numValuesChanged;
  string lastComponentName;
  TurtleGraphics* turtle;
  Slider* slider;
  StartListener(TurtleGraphics* TG, Slider* SS) {
    numActionsPerformed = 0;
    numValuesChanged = 0;
    lastComponentName = "";
    turtle = TG;
    slider = SS;
  }

  virtual void ValueChanged(Component * c) {
    
    numActionsPerformed++;
    //astComponentName = TG->GetName();
    turtle->GoToStep(0);
    slider->SetCurValue(0);
    LOG(INFO) << "we heree";
  }
  virtual void ActionPerformed(Component* c) {
    
    numActionsPerformed++;
    //lastComponentName = TG->GetName();
    turtle->GoToStep(0);
    slider->SetCurValue(0);
    LOG(INFO) << "We here2";
  }
};


class NextListener : public ActionListener, public ValueListener {
public:
  int numActionsPerformed;
  int numValuesChanged;
  string lastComponentName;
  TurtleGraphics* turtle;
  Slider* slider;
  NextListener(TurtleGraphics* TG, Slider* SS) {
    numActionsPerformed = 0;
    numValuesChanged = 0;
    lastComponentName = "";
    turtle = TG;
    slider = SS;
  }

  virtual void ValueChanged(Component * c) {
    
    numActionsPerformed++;
    //astComponentName = TG->GetName();
    turtle->GoToStep(slider->GetCurValue() + (turtle->GetNumSteps() / slider->GetMaximum())*slider->GetCurValue());
    slider->SetCurValue(numActionsPerformed);
    //LOG(INFO) << "we heree";
  }
  virtual void ActionPerformed(Component* c) {
    
    numActionsPerformed++;
    //lastComponentName = TG->GetName();
    turtle->GoToStep(slider->GetCurValue() + (turtle->GetNumSteps() / slider->GetMaximum())*slider->GetCurValue());
     slider->SetCurValue(numActionsPerformed);
    //LOG(INFO) << "We here2";
  }
};

class PrevListener : public ActionListener, public ValueListener {
public:
  int numActionsPerformed;
  int numValuesChanged;
  string lastComponentName;
  TurtleGraphics* turtle;
  Slider* slider;

  PrevListener(TurtleGraphics* TG, Slider* SS) {
    numActionsPerformed = 100;
    numValuesChanged = 0;
    lastComponentName = "";
    turtle = TG;
    slider = SS;
  }

  virtual void ValueChanged(Component * c) {
    
    numActionsPerformed--;
    //astComponentName = TG->GetName();
    turtle->GoToStep(slider->GetCurValue() + (turtle->GetNumSteps() / slider->GetMaximum())*slider->GetCurValue());
     slider->SetCurValue(numActionsPerformed);
    LOG(INFO) << "we heree";
  }
  virtual void ActionPerformed(Component* c) {
    
    numActionsPerformed--;
    //lastComponentName = TG->GetName();
    turtle->GoToStep(slider->GetCurValue() + (turtle->GetNumSteps() / slider->GetMaximum())*slider->GetCurValue());
     slider->SetCurValue(numActionsPerformed);
    LOG(INFO) << "We here2";
  }
};

  class StepListener : public ActionListener, public ValueListener {
public:
  int numActionsPerformed;
  int numValuesChanged;
  string lastComponentName;
  TurtleGraphics* turtle;
  Slider * slider;

  StepListener(TurtleGraphics* TG, Slider* SS) {
    numActionsPerformed = 0;
    numValuesChanged = 0;
    lastComponentName = "";
    turtle = TG;
    slider = SS;
  }

  virtual void ValueChanged(Component * c) {
    
    numActionsPerformed++;
    //astComponentName = TG->GetName();
    turtle->GoToStep(slider->GetCurValue() + (turtle->GetNumSteps() / slider->GetMaximum())*slider->GetCurValue());
    LOG(INFO) << "we heree";
  }
  virtual void ActionPerformed(Component* c) {
    
    numActionsPerformed++;
    //lastComponentName = TG->GetName();
    turtle->GoToStep(slider->GetCurValue());
    LOG(INFO) << "We here2";
  }
  /*
  virtual void ActionPerformed(Component* c) {
    
    numActionsPerformed++;
    lastComponentName = c->GetName();
    
    LOG(INFO) << "lastComponentName";
  }
  
  virtual void ValueChanged(Component* c) {
    numValuesChanged++;
    lastComponentName = c->GetName();
    //c->GoToStep(100);
  }
  */

};



EndListener *counter = new EndListener(turtle, stepSlider);
StepListener *counter2 = new StepListener(turtle, stepSlider);
StartListener * counter3 = new StartListener(turtle, stepSlider);
NextListener * counter4 = new NextListener(turtle, stepSlider);
PrevListener * counter5 = new PrevListener(turtle,stepSlider);
stepSlider->AddValueListener(counter2);
//nextButton->AddActionListener(counter);
startButton->AddActionListener(counter3);
endButton->AddActionListener(counter);
turtle->AddValueListener(counter);
nextButton->AddActionListener(counter4);
prevButton->AddActionListener(counter5);
//stepSlider->AddValueListener(counter);

  turtle->RotateInDegrees(45);
  turtle->Forward(100, true);
  //LOG_TODO << "sup1";
  turtle->RotateInDegrees(135);
  turtle->Forward(65, true);
  //LOG_TODO << "sup2";
  turtle->RotateInDegrees(135);
  turtle->Forward(100, true);
  //LOG_TODO << "sup3";
  turtle->RotateInDegrees(150);
  turtle->Forward(30, true);
//turtle->GoToStep(turtle->GetNumSteps()/2);

  //delete timer;
}

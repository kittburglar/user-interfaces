#include "Slider.h"
#include "XWindow.h"
#include "Logging.h"
#include "TurtleGraphics.h"
#include "XApplication.h"
//#include "Timer.h"
#include <algorithm>

using namespace cs349;
using namespace std;

Slider::Slider(const string & name, OrientationType orientation)
: Component(name)
{
  lowerBound = 0;
  upperBound = 0;
  curValue= 0;
  lowerBoundLabel = "";
  upperBoundLabel = "";
  thumbLabel = "";
  this->orientation = orientation;
  SetMaximum(100);
  SetMinimum(0);

  //timer->Start();
// TODO CS349
}

void Slider::AddValueListener(ValueListener* l) {
// TODO CS349
  listeners.push_back(l);

}

void Slider::RemoveValueListener(ValueListener* l) {
// TODO CS349
  remove(listeners.begin(), listeners.end(), l);

}

int Slider::GetMinimum() const {
  return lowerBound;
}

int Slider::GetMaximum() const {
  return upperBound;
}

int Slider::GetCurValue() const {
  return curValue;
}

void Slider::SetMinimum(int minValue) {
// TODO CS349
  this->lowerBound = minValue;
}

void Slider::SetMaximum(int maxValue) {
// TODO CS349
  this->upperBound = maxValue;
}

void Slider::SetCurValue(int value) {
// TODO CS349
  if(value > 100 || value == 99)
    this->curValue = 100;
  else if(value < 0)
    this->curValue = 0;
  else
    this->curValue = value;
  this->Repaint(GetBounds());
}

Slider::OrientationType Slider::GetOrientation() const {
  return orientation;
}

void Slider::SetOrientation(OrientationType orientation) {
  this->orientation = orientation;
  this->Repaint();
}

void Slider::PaintComponent(Graphics* g) {
// TODO CS349
  AffineTransform originalTransform = g->GetTransform();
  
  int colour = g->GetForegroundColor();
  g->DrawRect(this->GetBounds());
  g->SetTransform(originalTransform);
  g->SetForegroundColor(100000);
  //g->FillRect(Rectangle(250-202-(250-202)/2,30,130,20));
  g->SetForegroundColor(1);
  g->FillRect(GetBounds());
  g->SetForegroundColor(colour);
  g->FillRect(Rectangle(this->GetBounds().x, this->GetBounds().GetTopLeft().y + this->GetBounds().height/2, 101, 3));
  //Get the peg
  ostringstream strs;
  strs << GetCurValue();
  string str = strs.str();
  g->DrawText(Point(this->GetCurValue() + this->GetBounds().x, this->GetBounds().GetTopLeft().y + 15), str);
  g->FillRect(Rectangle(this->GetCurValue() + this->GetBounds().x , this->GetBounds().GetTopLeft().y + this->GetBounds().height/2 - 3, 10, 10));
  
  //Paint();
  //SetLowerBoundLabel("0");
  ////LOG(INFO) << "WE IN Slider::PaintComponent(Graphics* g) YEYE!:WE";
  //if()
  //this->Paint();
  //sleep(2);
}

string Slider::GetLowerBoundLabel() const {
  return lowerBoundLabel;
}

string Slider::GetUpperBoundLabel() const {
  return upperBoundLabel;
}

string Slider::GetThumbLabel() const {
  return thumbLabel;
}

void Slider::SetLowerBoundLabel(const string & label) {
  this->lowerBoundLabel = label;
  this->Repaint();
}

void Slider::SetUpperBoundLabel(const string & label) {
  this->upperBoundLabel = label;
  this->Repaint();
}

void Slider::SetThumbLabel(const string & label) {
  this->thumbLabel = label;
  this->Repaint();
}

// TODO CS349: Implement any other methods needed here

bool Slider::HandleMouseEvent(const MouseEvent& e) {
  //e.GetWindow()->SetMouseFocus(this);
  //LOG(INFO) << "The slider's current position is: " << this->GetCurValue();
  ////LOG(INFO) << "The current: " << this->timer->GetCurrentTime();
  ////LOG(INFO) << e.GetWhere().x << " and " << e.GetWhere().y << "and" << GetCurValue();
  //TODO better hit detection 
  if(e.GetType() == MouseEvent::mouseMove && GetThumbPressed() == true){
    //LOG(INFO) << "We dragging in the slider component";
    //e->this = 260;
    e.GetWindow()->SetMouseFocus(this);
    if(this->GetCurValue() < 0){
      SetCurValue(0);
    }
    else if(this->GetCurValue() > 100){
      SetCurValue(100);
    } 
    else{
      this->SetCurValue(e.GetWhere().x);
    }
    this->Repaint(this->GetBounds());  
    //BroadcastSliderChanged();   
    return true;
  }
  else if (e.GetType() == MouseEvent::mouseDown &&  e.GetWhere().x <= GetCurValue() + 10){
    //LOG(INFO) << "We pressed the slider thumb!";
    //add 260(left bound of button) to e
   
    //e.GetWindow()->SetMouseFocus(this);
    //sleep(1);
    SetThumbPressed(true);
    this->Repaint(this->GetBounds());  
    //sleep(1);
    return true;
  }
  else if (e.GetType() == MouseEvent::mouseUp && GetThumbPressed() == true){
    //LOG(INFO) << "We released the slider thumb!" << this->GetCurValue();
    //sleep(1);
    if(this->GetCurValue() == 99){
      SetCurValue(100);
    }
    else if(this->GetCurValue() < 0){
      SetCurValue(0);
    }
    else if(this->GetCurValue() > 100 || this->GetCurValue() == 99 ){
      SetCurValue(100);

    }
    else{
      this->SetCurValue(e.GetWhere().x);
    }
    SetThumbPressed(false);
    this->GetParentWindow()->ClearMouseFocus();
    this->Repaint(this->GetBounds());  
    BroadcastSliderChanged();
    return true;
  }
  else if (e.GetType() == MouseEvent::mouseDown){
    //LOG(INFO) << "We pressed the mouse down on the slider";
    if(this->GetCurValue() < 0){
      SetCurValue(0);
    }
    else if(this->GetCurValue() > 100 || this->GetCurValue()  == 99){
      SetCurValue(100);
    }
    else{
      this->SetCurValue(e.GetWhere().x);
    }
    this->Repaint(this->GetBounds());        
    BroadcastSliderChanged();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
    return true;
  }
  return false;

}



void Slider::SetThumbPressed(const bool state){
  this->thumbPressed = state;
}

bool Slider::GetThumbPressed() const{
  return this->thumbPressed;
}

void Slider::BroadcastSliderChanged() {
  this->NotifyListeners();
}

void Slider::NotifyListeners() {
  for (vector<ValueListener*>::iterator i = listeners.begin(); i != listeners.end(); i++) {
    (*i)->ValueChanged(this);
  }
}

double Slider::GetXCoord() const{
  return this->xcoord;
}

void Slider::SetXCoord(const double coord){
  this->xcoord = coord;
}


// TODO CS349

// Author: Michael Terry
#include "Button.h"
#include "XWindow.h"
#include "Logging.h"
#include "ActionListener.h"
#include "TurtleGraphics.h"
#include <algorithm>

using namespace cs349;

Button::Button(const string & name, const string & label)
: Component(name)
{
  // Perform any initialization needed here
	this->label = label;
	labelpressed = "";
// TODO CS349
}

void Button::AddActionListener(ActionListener* l) {
// TODO CS349
	listeners.push_back(l);
}

void Button::RemoveActionListener(ActionListener* l) {
// TODO CS349
	remove(listeners.begin(), listeners.end(), l);
}

string Button::GetLabel() const {
	return label;
}

void Button::SetLabel(const string & label) {
	this->label = label;
}

void Button::PaintComponent(Graphics* g) {
	g->DrawRect(this->GetBounds());
	if(GetLabel() == "Start"){
		g->FillRect(Rectangle(this->GetBounds().x + 10,this->GetBounds().y + 5,3,30));
		for(int i = 0; i < 15; i++)
			g->DrawRect(Rectangle(this->GetBounds().x+i + 15,this->GetBounds().y + this->GetBounds().height/2-i,1,2*i));
	}
	else if(GetLabel() == "Previous"){
		for(int j = 0; j < 15; j++)
			g->DrawRect(Rectangle(this->GetBounds().x+j + 10,this->GetBounds().y + this->GetBounds().height/2-j,1,2*j));
	}

	else if(GetLabel() == "Play"){
		for(int k = 0; k < 15; k++)
			g->DrawRect(Rectangle(this->GetBounds().x - k + 30,this->GetBounds().y + this->GetBounds().height/2-k,1,2*k));
	}
	else if(GetLabel() == "Next"){
		for(int l = 0; l < 15; l++)
			g->DrawRect(Rectangle(this->GetBounds().x - l + 30,this->GetBounds().y + this->GetBounds().height/2-l,1,2*l));
	}	
	else if(GetLabel() == "End"){
		for(int m = 0; m < 15; m++)
			g->DrawRect(Rectangle(this->GetBounds().x - m + 20,this->GetBounds().y + this->GetBounds().height/2-m,1,2*m));
		g->FillRect(Rectangle(this->GetBounds().x + 30,this->GetBounds().y + 5,3,30));
	}
// TODO CS349
}

bool Button::HandleMouseEvent(const MouseEvent& e) {
	//LOG(INFO) << "The mouse event is at location: " << e.GetWhere() << "and the componenet start is at: " << this->GetBounds().x;
	if (e.GetType() == MouseEvent::mouseDown){
		SetButtonPressed(true);
		//e.GetWindow()->SetMouseFocus(this);
		////LOG(INFO) << "We pressed down a button";
		
		//LOG(INFO) << "We just pressed " << GetLabel() << " button!";
		SetLabelPressed(GetLabel());
		
			//e.GetWindow()->ClearMouseFocus();
		return true;

		
	}
	////LOG(INFO) << "The mouse event is at location: " << e.GetWhere() << "and the componenet start is at: " << this->GetBounds().x;
	else if(e.GetType() == MouseEvent::mouseUp){
		if(GetButtonPressed() == true ){
			//LOG(INFO) << "We released on the " << GetLabel() << " button!";
			NotifyListeners();
			//e.GetWindow()->ClearMouseFocus();
			SetButtonPressed(false);
			return true;
		}
		else{
			//e.GetWindow()->ClearMouseFocus();
			//LOG(INFO) << "We failed becaused we released on the " << GetLabel() << " button!";
			SetButtonPressed(false);
			return false;
		}
		//e.GetWindow()->SetMouseFocus(this);
		//FIX THIS
		/*
		if(this->GetLabel() == "Start" && GetLabelPressed() == "Start"){
			//LOG(INFO) << "We just pressed Start button!";
			e.GetWindow()->ClearMouseFocus();
			//TurtleGraphics::GoToStep(10);
			return true;
		}
		else if(this->GetLabel() == "Previous" && GetLabelPressed() == "Previous"){
			//LOG(INFO) << "We just pressed Previous button!";
			e.GetWindow()->ClearMouseFocus();
			return true;
		}
		else if(this->GetLabel() == "Play" && GetLabelPressed() == "Play"){
			//LOG(INFO) << "We just pressed Play button!";
			e.GetWindow()->ClearMouseFocus();
			return true;
		}
		else if(this->GetLabel() == "Next" && GetLabelPressed() == "Next"){
			//LOG(INFO) << "We just pressed Next button!";
			e.GetWindow()->ClearMouseFocus();
			return true;
		}
		else if(this->GetLabel() == "End" && GetLabelPressed() == "End"){
			//LOG(INFO) << "We just pressed End button!";
			e.GetWindow()->ClearMouseFocus();
			return true;
		}
		*/
		//return true;
	}
	return false;
}

void Button::SetLabelPressed(const string & label){
	labelpressed = label;
}

string Button::GetLabelPressed(){
	return labelpressed; 
}

void Button::BroadcastButtonPressed() {
	this->NotifyListeners();
}

void Button::NotifyListeners() {
	for (vector<ActionListener*>::iterator i = listeners.begin(); i != listeners.end(); i++) {
		(*i)->ActionPerformed(this);
	}
}

void Button::SetButtonPressed(const bool state){
	this->buttonPressed = state;
}

bool Button::GetButtonPressed() const{
	return this->buttonPressed;
}

void Button::SetTurtleGraphics(TurtleGraphics* turtle){
  TG = turtle;
}

TurtleGraphics* Button::GetTurtleGraphics(){
	return this->TG;
}
// TODO CS349: Implement any other methods needed here
// TODO CS349

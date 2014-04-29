// Author: Kittiphong Xayasane
#include "Label.h"
#include "XWindow.h"
#include "Logging.h"
#include "ActionListener.h"

#include <algorithm>

using namespace cs349;

Label::Label(const string & name, const string & label)
: Component(name)
{
  // Perform any initialization needed here
	this->label = label;
// TODO CS349
}


string Label::GetLabel() const {
	return label;
}

void Label::SetLabel(const string & label) {
	this->label = label;
}

void Label::PaintComponent(Graphics* g) {
	g->DrawRect(this->GetBounds());
	g->DrawText(Point(GetBounds().x, GetBounds().y + GetBounds().height/2), this->label);
// TODO CS349
}



// TODO CS349: Implement any other methods needed here
// TODO CS349

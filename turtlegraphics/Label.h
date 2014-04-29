// Author: Kittiphong Xayasane
#ifndef _LABEL_H_
#define _LABEL_H_

#include "Component.h"
#include <vector>
#include "ActionListener.h"
using namespace std;

namespace cs349 {

  class Label : public Component {
  private:
    string label;

  protected:

    virtual void PaintComponent(Graphics* g);

  public:

    Label(const string & name, const string & label);
    
    string GetLabel() const;

    /**
     * Sets the text label for this button
     */
    void SetLabel(const string & label);

  };
}


#endif /* _LABEL_H_ */

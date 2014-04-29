#include "XApplication.h"
#include "StopWatch.h"
#include <sstream>
#include <time.h>
#include <sstream>
using namespace std;

namespace cs349 {

  StopWatch::StopWatch(EventQueue* eventQueue, const Rectangle & bounds) : XWindow(string("Stop Watch!"), bounds) {
    this->running = false;
    this->paused = false; //(ADDED)
    this->reset = false;
    this->timer = new Timer(eventQueue, 100, true, this);
    timer->Start();
    startTimeMS = GetCurTime();
    gapTimeMS = 0;
    outputTimeMS = 0;
    outputTimeSECS = 0;
    outputTimeMINS = 0;
    fillHeightMS = 0;
    fillHeightSECS = 0;
  }

  StopWatch::~StopWatch() {
    timer->Stop();
    delete timer;
    timer = NULL;
  }

  unsigned long StopWatch::GetCurTime() {
    struct timespec tp;
    clock_gettime(CLOCK_REALTIME, &tp);
    return tp.tv_sec * 1000 + tp.tv_nsec / 1000000;
  }
	
  void StopWatch::Paint(Graphics* g) {
// TODO CS349
    //LOG(INFO) << "We are in StopWatch::Paint";

    //LOG(INFO) << "Time is: " << GetCurTime() - startTimeMS;
    //LOG(INFO) << "We want to set forground to colour 10";
    //g->SetForegroundColor(10);
    string number;
    string numbersecs;
    string numbermins;
    stringstream strstream;
    stringstream strstreamsecs;
    stringstream strstreammins;
    //strstream << GetCurTime() - startTimeMS;
    strstream << outputTimeMS;
    strstreamsecs << outputTimeSECS;
    strstreammins << outputTimeMINS;
    strstream >> number;
    strstreamsecs >> numbersecs;
    strstreammins >> numbermins;

    g->SetForegroundColor(XApplication::GetInstance()->GetBlackColor());
    g->FillRect(Rectangle(0,0,200,50));
    g->SetForegroundColor(XApplication::GetInstance()->GetWhiteColor());
    g->DrawText(Point(55,20), number + "ms");
    g->DrawText(Point(55,30), numbersecs + "secs");
    g->DrawText(Point(55,40), numbermins + "mins");
    if(this->IsPaused() || this->IsReset()){
      g->SetForegroundColor(XApplication::GetInstance()->GetBlackColor());
      g->FillRect(Rectangle(6,6,38,38));
      g->SetForegroundColor(XApplication::GetInstance()->GetWhiteColor());
      g->DrawText(Point(11, 30), "Start");
    }
    else if(this->IsRunning() || this->IsReset()){
      g->SetForegroundColor(XApplication::GetInstance()->GetBlackColor());
      g->FillRect(Rectangle(6,6,38,38));
      g->SetForegroundColor(XApplication::GetInstance()->GetWhiteColor());
    
      g->DrawText(Point(14, 30), "Stop");
    }
    else{
      g->DrawText(Point(11, 30), "Start");
    }
    g->DrawText(Point(159,30), "Reset");
    g->DrawRect(Rectangle(5, 5, 40, 40));
    
    if(this->IsRunning()){
      g->DrawRect(Rectangle(6, 6, 38, 38));
    }
    g->DrawRect(Rectangle(153, 5 ,40, 40));
    g->DrawRect(Rectangle(50, 5 ,98, 40));
    g->DrawRect(Rectangle(118, 10 ,5, 30));
    g->DrawRect(Rectangle(128, 10 ,5, 30)); //second bar
    g->DrawRect(Rectangle(138, 10 ,5, 30));
    g->FillRect(Rectangle(118, 40 - (outputTimeMS % 1000) / 33.3333, 5, 40 - (40 - (outputTimeMS % 1000) / 33.3333)));
    //g->FillRect(Rectangle(128, 40 - (outputTimeSECS % 60) / 2, 5, 1));
    g->FillRect(Rectangle(128, 40 - (outputTimeSECS % 60) / 2, 5, 40- (40 - (outputTimeSECS % 60) / 2)));
    g->FillRect(Rectangle(138, 40 - (outputTimeMINS % 10) / 0.3333, 5, 40 - (40 - (outputTimeMINS % 10) / 0.3333)));
    //g->PrepareGraphicsContext();
    //g->SetForegroundColor(1000);
  }
	
  bool StopWatch::HandleMouseEvent(const MouseEvent & e) {
    // TODO: Remove the following code and add your own event handling
    // code to handle mouse events
    //Click in
    Rectangle button1 = Rectangle(5,5,40,40);
    Rectangle button2 = Rectangle(153,5,40,40);
    e.GetWhere();
    if ((button1.IsPointInRectangle(e.GetWhere())) && (e.GetType() == MouseEvent::mouseUp)){
      //LOG(INFO) << "We clicked button1!";
      if(this->IsRunning()){
        //LOG(INFO) << "The stopwatch is running and we are stopping it.";
        this->Stop();
      }
      else if(this->IsPaused()){
        //LOG(INFO) << "The stopwatch is paused and we are unpausing it";
        this->UnPause();

      }
      else{
        //Restart
        //LOG(INFO) << "The stopwatch is starting for the first time";
        //this->Start();
        this->Start();
      }
    }
    else if ((button2.IsPointInRectangle(e.GetWhere())) && (e.GetType() == MouseEvent::mouseUp)){
      //LOG(INFO) << "We clicked button2!";
      if(this->IsRunning()){
        //LOG(INFO) << "The stopwatch is running.";
      }
      else{
        this->Reset();
        //LOG(INFO) << "The stopwatch is not running we are restarting";
      }
    }
    LOG_DEBUG << "Received HandleMouseEvent in StopWatch" << e;

// TODO CS349
    return true;
  }

  void StopWatch::HandleTimerEvent(const cs349::TimerEvent & e) {
    if (running) {
      outputTimeMS = GetCurTime() - startTimeMS;
      outputTimeSECS = outputTimeMS / 1000;
      outputTimeMINS = outputTimeSECS / 60;
      Component::Repaint();
    }
  }

  void StopWatch::Start() {
    this->running = true;
    this->paused = false;
    startTimeMS = GetCurTime();
    outputTimeMS = startTimeMS;
    outputTimeSECS = outputTimeMS;
    outputTimeMINS = outputTimeSECS;
  }

  void StopWatch::UnPause() {
    gapTimeMS = GetCurTime() - gapTimeMS;
    startTimeMS = startTimeMS + gapTimeMS;
    this->running = true;
    this->paused = false;
  }

  void StopWatch::Stop() {
    gapTimeMS = GetCurTime();
    this->running = false;
    this->paused = true;
    Component::Repaint();
  }

  void StopWatch::Reset(){
    this->running = false;
    this->paused = false;
    this->reset = true;
    startTimeMS = GetCurTime();
    outputTimeMS = GetCurTime() - startTimeMS;
    outputTimeSECS = outputTimeMS / 1000;
    outputTimeMINS = outputTimeSECS / 60;
    //startTimeMS = 0;
    Component::Repaint();
  }
  bool StopWatch::IsRunning() const {
    return running;
  }
  bool StopWatch::IsPaused() const {
    return paused;
  }

  bool StopWatch::IsReset() const {
    return reset;
  }
}

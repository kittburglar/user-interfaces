// A1 Unit Tests

#include "gtest/gtest.h"
#include "StopWatch.h"
#include "XApplication.h"

using namespace cs349;

TEST(A1UnitTestDemos, Construction) {
  StopWatch* watch = new StopWatch(XApplication::GetInstance()->GetEventQueue(), Rectangle(0, 0, 200, 75));
  EXPECT_FALSE(watch == NULL);
  EXPECT_FALSE(watch->IsRunning());

  EventQueue* queue = XApplication::GetInstance()->GetEventQueue();
  EXPECT_FALSE(queue == NULL);
  queue->ClearEventQueue(); // Need to clear the event queue of any
                            // events our watch window has generated,
                            // or else subsequent processing of those
                            // events will fail miserably (i.e., core
                            // dump) if events from the event queue
                            // are processed after the watch pointer
                            // is deleted below.

  delete watch;
}

TEST(A1UnitTestDemos, Interaction) {
  XApplication* app = XApplication::GetInstance();
  StopWatch* watch = new StopWatch(app->GetEventQueue(), Rectangle(0, 0, 200, 50));
  EXPECT_FALSE(watch == NULL);

  // Create a synthetic mouse event to test whether watch responds to it
  // or not. Note that this assumes that clicking in the location
  // specified amounts to pressing the start/stop button. Your actual
  // interaction will likely be different, making this test useless.
  // However, this should provide a template for how to do unit tests
  // for interaction.
  //XApplication* app = XApplication::GetInstance();
  //scheme_interpreter_init(scheme_init_fname, cs349_init_fname, argc, argv);
  //StopWatch* stopWatchWindow = new StopWatch(app->GetEventQueue(), Rectangle(0, 0, 200, 50));
  watch->SetVisible(true);

  EXPECT_FALSE(watch->GetParentWindow() == NULL);
  Event* p = new PaintEvent(watch->GetParentWindow(),watch->GetParentWindow()->GetBounds());
  MouseEvent* e = new MouseEvent(watch->GetParentWindow(), MouseEvent::mouseUp, Point(10, 10));
  MouseEvent* e2 = new MouseEvent(watch->GetParentWindow(), MouseEvent::mouseUp, Point(10, 10));
  MouseEvent* e3 = new MouseEvent(watch->GetParentWindow(), MouseEvent::mouseUp, Point(160, 10));
  EventQueue* queue = XApplication::GetInstance()->GetEventQueue();
  EXPECT_FALSE(queue == NULL);

  EXPECT_FALSE(watch->IsRunning()); //TEST OUR START STATE

  queue->AddEventToQueue(p);
  
  unsigned int max_num_tries_to_flush_queue = 10;
  for(int startbuf = 0; startbuf < 1000; startbuf++)
  //while (max_num_tries_to_flush_queue-- > 0)
    {
      //LOG(INFO) << "looping";
      queue->ProcessNextEvent();
      XFlush(app->GetXDisplay());
      usleep(1000);
    }
  queue->AddEventToQueue(e);
  
  for(int startbuf2 = 0; startbuf2 < 1000; startbuf2++)
  //while (max_num_tries_to_flush_queue-- > 0)
    {
      //LOG(INFO) << "looping";
      queue->ProcessNextEvent();
      XFlush(app->GetXDisplay());
      usleep(1000);
    }
  EXPECT_TRUE(watch->IsRunning()); //TEST OUR RUNNING STATE

      queue->AddEventToQueue(e2);
     for(int startbuf3; startbuf3 < 1000; startbuf3++)
    {
      queue->ProcessNextEvent();
      XFlush(app->GetXDisplay());
      usleep(1000);
    }
    EXPECT_TRUE(watch->IsPaused()); //TEST OUR PAUSE STATE

  queue->AddEventToQueue(e3);
  for(int startbuf4; startbuf4 < 1000; startbuf4++)
    {
      queue->ProcessNextEvent();
      XFlush(app->GetXDisplay());
      usleep(1000);
    }
  EXPECT_TRUE(watch->IsReset()); //TEST OUR RESET STATE
  //ADD C
  //EXPECT_TRUE(watch->IsRunning());
  



  queue->ClearEventQueue();
  delete watch;
  // We do not need to delete the mouse event that we created, because
  // it will be deleted automatically by the EventQueue.
}

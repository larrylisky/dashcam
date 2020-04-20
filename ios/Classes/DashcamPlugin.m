#import "DashcamPlugin.h"
#if __has_include(<dashcam/dashcam-Swift.h>)
#import <dashcam/dashcam-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "dashcam-Swift.h"
#endif

@implementation DashcamPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftDashcamPlugin registerWithRegistrar:registrar];
}
@end
